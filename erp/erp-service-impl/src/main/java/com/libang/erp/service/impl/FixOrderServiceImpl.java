package com.libang.erp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.libang.erp.dto.OrderDto;
import com.libang.erp.dto.OrderStateDto;
import com.libang.erp.entity.*;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.mapper.FixOrderMapper;
import com.libang.erp.mapper.FixOrderPartsMapper;
import com.libang.erp.mapper.OrderTimeOutMapper;
import com.libang.erp.service.FixOrderService;
import com.libang.erp.springQuartz.CheckOrderTimeOut;
import com.libang.erp.util.Constant;
import com.libang.erp.vo.FixOrderPartsVo;
import org.joda.time.DateTime;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/8/9 10:16
 */
@Service
public class FixOrderServiceImpl implements FixOrderService {

    @Autowired
    private FixOrderMapper fixOrderMapper;
    @Autowired
    private FixOrderPartsMapper fixOrderPartsMapper;

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private OrderTimeOutMapper orderTimeOutMapper;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;



    /**
     * 封装订单的json对象，
     *
     * @param json
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void createFixOrder(String json) {
        //解析json
        OrderDto orderDto = new Gson().fromJson(json, OrderDto.class);

        //解析OrderDto并入库

        FixOrder fixOrder = new FixOrder();

        //订单
        fixOrder.setOrderId(orderDto.getOrder().getId());
        fixOrder.setOrderMoney(orderDto.getOrder().getOrderMoney());
        fixOrder.setOrderTime(orderDto.getOrder().getCreateTime());
        //服务类型
        fixOrder.setOrderType(orderDto.getServiceType().getServiceName());
        fixOrder.setState(orderDto.getOrder().getState());
        fixOrder.setOrderServiceHour(orderDto.getServiceType().getServiceHour());

        //车主信息
        fixOrder.setCustomerName(orderDto.getOrder().getCustomer().getUserName());
        fixOrder.setCustomerTel(orderDto.getOrder().getCustomer().getTel());

        //计算工时费用
        fixOrder.setOrderServiceHourFee(new BigDecimal(Integer.parseInt(orderDto.getServiceType().getServiceHour()) * Constant.DEFAULT_HOUR_FEE));
        //计算配件费用
        fixOrder.setOrderPartsFee(fixOrder.getOrderMoney().subtract(fixOrder.getOrderServiceHourFee()));

        //封装车辆信息
        fixOrder.setCarLicence(orderDto.getOrder().getCar().getLicenceNo());
        fixOrder.setCarColor(orderDto.getOrder().getCar().getColor());
        System.out.println("车辆颜色" + orderDto.getOrder().getCar().getColor());
        fixOrder.setCarType(orderDto.getOrder().getCar().getCarType());

        fixOrderMapper.insert(fixOrder);

        //封装配件入库子信息
        for (Parts parts : orderDto.getPartsList()) {

            FixOrderParts fixOrderParts = new FixOrderParts();
            fixOrderParts.setOrderId(orderDto.getOrder().getId());
            fixOrderParts.setPartsId(parts.getId());
            fixOrderParts.setPartsName(parts.getPartsName());
            fixOrderParts.setPartsNo(parts.getPartsNo());
            fixOrderParts.setPartsNum(parts.getOrderParts().getNum());
            fixOrderParts.setTypeName(parts.getType().getTypeName());
            fixOrderPartsMapper.insertSelective(fixOrderParts);


        }

    }

    /**
     * 查询维修订单详情
     *
     * @param orderMap
     * @return
     */
    @Override
    public PageInfo<FixOrder> findPageByParam(Map<String, Object> orderMap) {
        PageHelper.startPage(Integer.parseInt(String.valueOf(orderMap.get("pageNo"))), Constant.DEFAULT_PAGE_SIZE);

        FixOrderExample fixOrderExample = new FixOrderExample();
        List<FixOrder> fixOrderList = fixOrderMapper.selectByExample(fixOrderExample);
        /*  List<FixOrder> fixOrderList = fixOrderMapper.findAllOrderWithParts();*/
        for (FixOrder fixOrder : fixOrderList) {
            List<FixOrderParts> fixOrderPartsList = fixOrderPartsMapper.findByOrderId(fixOrder.getOrderId());
            fixOrder.setFixOrderPartsList(fixOrderPartsList);
        }

        PageInfo<FixOrder> orderPageInfo = new PageInfo<>(fixOrderList);

        return orderPageInfo;
    }

    /**
     * 领取任务根据订单id,和登录人员跟新订单状态，和记住该订单的维修人员
     *
     * @param id
     * @param employee
     */
    @Override
    public void taskReceive(Integer id, Employee employee) {

        //  判断当前维修人员是否有订单
        FixOrderExample fixOrderExample = new FixOrderExample();
        fixOrderExample.createCriteria().andFixEmployeeIdEqualTo(employee.getId()).andStateEqualTo(FixOrder.ORDER_STATE_FIXING);
        List<FixOrder> fixOrderList = fixOrderMapper.selectByExample(fixOrderExample);
        if (fixOrderList != null && fixOrderList.size() > 0) {
            throw new ServiceException("还有未完成的任务不能接受新任务");
        }

        //根据订单ID查询订单
        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(id);
        if (fixOrder == null) {
            throw new ServiceException("参数错误或该订单不存在");
        }
        //如果存在设置订单的状态修改为3
        fixOrder.setState(FixOrder.ORDER_STATE_FIXING);
        //设置维修人员的id,和姓名
        fixOrder.setFixEmployeeId(employee.getId());
        fixOrder.setFixEmployeeName(employee.getEmployeeName());
        //更新数据库
        fixOrderMapper.updateByPrimaryKeySelective(fixOrder);

        //封装数据发送到消息队列
        OrderStateDto orderStateDto = new OrderStateDto();
        orderStateDto.setOrderId(id);
        orderStateDto.setState(FixOrder.ORDER_STATE_FIXING);
        orderStateDto.setEmployeeId(employee.getId());

        sendStateToMQ(orderStateDto);

        //  减少库存

        changePartsInventory(id, employee.getId());


        //添加超时的定时任务

            setFixOrderTimeOutTask(id,employee.getId(),Integer.parseInt(fixOrder.getOrderServiceHour()));



    }


    /**
     *
     * 数据库添加超时的定时任务
     * @param orderId 订单Id
     * @param employyeeId 员工Id
     * @param serverHour 服务时间
     */
    private void setFixOrderTimeOutTask(Integer orderId, Integer employyeeId, int serverHour) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
        //设置添加数据库的格式
        JobDetail jobDetail = JobBuilder.newJob(CheckOrderTimeOut.class)
                                        .withIdentity("fix:"+orderId+"-"+employyeeId,"fixOrder")
                                        .build();
        //拼接Cron表达式
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusHours(serverHour);
        String cronExpression = dateTime.getSecondOfMinute()+" "+dateTime.getMinuteOfHour()+" "+dateTime.getMillisOfDay()
                                +" "+dateTime.getDayOfMonth()+" "+dateTime.getMonthOfYear()+" ? "+ dateTime.getYear();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();

            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    /**
     * 根据订单Id和维修人员ID减少库存
     * 将数放入消息队列中
     *
     * @param id
     * @param employeeId
     */

    private void changePartsInventory(Integer id, Integer employeeId) {
        //根据订单Id查询配件数量
        FixOrderPartsExample fixOrderPartsExample = new FixOrderPartsExample();
        fixOrderPartsExample.createCriteria().andOrderIdEqualTo(id);
        List<FixOrderParts> fixOrderPartsList = fixOrderPartsMapper.selectByExample(fixOrderPartsExample);

        FixOrderPartsVo fixOrderPartsVo = new FixOrderPartsVo();
        fixOrderPartsVo.setEmployeeId(employeeId);
        fixOrderPartsVo.setFixOrderPartsList(fixOrderPartsList);

        String json = new Gson().toJson(fixOrderPartsVo);
        jmsTemplate.send("partsNum-queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });


    }


    /**
     * 根据订单Id查询订单
     *
     * @param id
     * @return
     */
    @Override
    public FixOrder getFixOrderId(Integer id) {


        return fixOrderMapper.findPartsByOrderId(id);
    }

    /**
     * 完成检修
     * 根据订单Id进行跟新数据库
     * 修改订单的状态
     *
     * @param id
     */
    @Override
    public void taskDone(Integer id) {
        //根据订单Id 查询订单的
        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(id);
        if (fixOrder == null) {
            throw new ServiceException("参数错误或该订单不存在");
        }
        fixOrder.setState(FixOrder.ORDER_STATE_FIXED);
        //跟新数据库
        fixOrderMapper.updateByPrimaryKeySelective(fixOrder);

        //封装json对象发送到队列中
        OrderStateDto orderStateDto = new OrderStateDto();
        orderStateDto.setOrderId(id);
        orderStateDto.setState(FixOrder.ORDER_STATE_FIXED);
        sendStateToMQ(orderStateDto);

        //删除超时的定时任务
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.deleteJob(new JobKey("fix:"+fixOrder.getOrderId()+"-"+fixOrder.getFixEmployeeId(),"fixOrder"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 领取检修任务根据订单id,和登录人员更新订单状态，和记住该订单的检修人员
     *
     * @param id
     * @param employee
     */
    @Override
    public void taskReceiveCheck(Integer id, Employee employee) {
        //判断当前人员是否有检修任务
        FixOrderExample fixOrderExample = new FixOrderExample();
        fixOrderExample.createCriteria().andFixEmployeeIdEqualTo(employee.getId()).andStateEqualTo(FixOrder.ORDER_STATE_CHECKING);
        List<FixOrder> fixOrderList = fixOrderMapper.selectByExample(fixOrderExample);
        if (fixOrderList != null && fixOrderList.size() > 0) {
            throw new ServiceException("您还有未完成的检修任务，不能领取新的任务");
        }
        //根据订单Id修改订单状态
        //根据订单Id记录检修人员的姓名和id
        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(id);
        fixOrder.setState(FixOrder.ORDER_STATE_CHECKING);
        fixOrder.setCheckEmployeeId(employee.getId());
        fixOrder.setCheckEmployeeName(employee.getEmployeeName());
        //跟新数据库
        fixOrderMapper.updateByPrimaryKeySelective(fixOrder);

        //进行封装
        OrderStateDto orderStateDto = new OrderStateDto();
        orderStateDto.setOrderId(id);
        orderStateDto.setState(FixOrder.ORDER_STATE_CHECKING);
        orderStateDto.setEmployeeId(employee.getId());

        //将订单状态发送到消息队列，进行跟新前台接待人员订单的状态
        sendStateToMQ(orderStateDto);

    }

    /**
     * 质检完成
     * 根据订单Id修改数据库订单的质检状态
     *
     * @param id
     */
    @Override
    public void taskDoneCheck(Integer id) {
        //根据订单Id查询订单，判断该订单是否存在
        FixOrder fixOrder = fixOrderMapper.selectByPrimaryKey(id);
        if (fixOrder == null) {
            throw new ServiceException("参数错误或该订单不存在");
        }
        //如果存在，修改订单状态，跟新数据库
        fixOrder.setState(FixOrder.ORDER_STATE_CHECKED);
        fixOrderMapper.updateByPrimaryKeySelective(fixOrder);


        //封装订单状态，发送到消息队列
        OrderStateDto orderStateDto = new OrderStateDto();
        orderStateDto.setOrderId(id);
        orderStateDto.setState(FixOrder.ORDER_STATE_CHECKED);

        sendStateToMQ(orderStateDto);


    }

    /**
     * 记录订单超时次数
     *
     * @param jobName
     */
    @Override
    public void addOrderTimeOut(String jobName) {

        //获取订单id
        Integer orderId = Integer.parseInt(jobName.split(":")[1].split("-")[0]);
        //获取员工id
        Integer employeeId = Integer.parseInt(jobName.split(":")[1].split("-")[1]);

        //获取服务的时间,判断超时的次数
        Integer serviceHour = Integer.parseInt(jobName.split(":")[1].split("-")[2]);


        //添加数据库
        OrderTimeOutExample orderTimeOutExample = new OrderTimeOutExample();
        orderTimeOutExample.createCriteria().andEmployeeIdEqualTo(employeeId).andOrderIdEqualTo(orderId);

        //获取超时的List集合
        List<OrderTimeOut> orderTimeOutList = orderTimeOutMapper.selectByExample(orderTimeOutExample);
        //判断该订单是否已经超时过，如果超时过则 num ++

        if(orderTimeOutList != null && orderTimeOutList.size()>0){

            OrderTimeOut orderTimeOut = orderTimeOutList.get(0);
            orderTimeOut.setNum(orderTimeOut.getNum()+1);
            //更新数据库
            orderTimeOutMapper.updateByPrimaryKeySelective(orderTimeOut);
        }else{
            //该订单如果没有超时过，进行添加
            OrderTimeOut orderTimeOut = new OrderTimeOut();
            orderTimeOut.setOrderId(orderId);
            orderTimeOut.setEmployeeId(employeeId);
            orderTimeOutMapper.insertSelective(orderTimeOut);
        }

    }


    /**
     * 发送订单状态到消息队列
     *
     * @param orderStateDto
     */
    private void sendStateToMQ(OrderStateDto orderStateDto) {
        //将对象转换为json数据传送的mq
        String json = new Gson().toJson(orderStateDto);
        jmsTemplate.send("state-queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });


    }


}
