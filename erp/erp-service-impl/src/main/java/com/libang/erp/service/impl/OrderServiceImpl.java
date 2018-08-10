package com.libang.erp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libang.erp.dto.OrderDto;
import com.libang.erp.dto.OrderStateDto;
import com.libang.erp.entity.*;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.mapper.*;
import com.libang.erp.service.OrderService;
import com.libang.erp.util.Constant;
import com.libang.erp.vo.OrderVo;
import com.libang.erp.vo.PartsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Map;




/**
 * @author libang
 * @date 2018/8/3 20:35
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderPartsMapper orderPartsMapper;

    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private OrderEmployeeMapper orderEmployeeMapper;

    @Autowired
    private PartsMapper partsMapper;

    @Autowired
    private JmsTemplate jmsTemplate;
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 查询所有服务类型列表
     *
     * @return
     */
    @Override
    public List<ServiceType> findAllServiceType() {
        ServiceTypeExample serviceTypeExample = new ServiceTypeExample();
        return serviceTypeMapper.selectByExample(serviceTypeExample);
    }

    /**
     * 查询所有配件类型
     *
     * @return
     */
    @Override
    public List<Type> findAllPartsType() {
        TypeExample typeExample = new TypeExample();
        return typeMapper.selectByExample(typeExample);
    }

    /**
     * 保存订单
     *
     * @param orderVo
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOrder(OrderVo orderVo,Employee employee) {

        Order order = new Order();

        order.setCarId(orderVo.getCarId());
        order.setOrderMoney(orderVo.getFee());
        order.setServiceTypeId(orderVo.getServiceTypeId());
        /*新增订单*/
        orderMapper.insertSelective(order);
        Integer orderId = order.getId();

        /*添加订单和员工的关联关系表*/
        OrderEmployee orderEmployee = new OrderEmployee();
        orderEmployee.setOrderId(orderId);
        orderEmployee.setEmployeeId(employee.getId());
        orderEmployeeMapper.insertSelective(orderEmployee);

        /*添加订单与配件的关联系表*/
        List<PartsVo> partsVoList =  orderVo.getPartsList();
        addOrderParts(order.getId(),partsVoList);

        /*if(partsVoList !=null &&    for(PartsVo partsVo : partsVoList){
        OrderParts orderParts = new OrderParts();partsVoList.size()>0){

                orderParts.setOrderId(orderId);
                orderParts.setPartsId(partsVo.getId());
                orderParts.setNum(partsVo.getNum());
        orderPartsMapper.insertSelective(orderParts);
            }
        }*/
    }




    /**
     * 查询所有订单
     *
     * @param orderMap
     * @return
     */
    @Override
    public List<Order> findAllOrder(Map<String, Object> orderMap) {
        List<Order> orderList = orderMapper.findAllOrder(orderMap);
        return orderList;
    }

    /**
     * 根据条件进行查询订单列表
     *
     * @param orderMap
     * @return
     */
    @Override
    public PageInfo<Order> findPageByParam(Map<String, Object> orderMap) {

        PageHelper.startPage(Integer.parseInt(String.valueOf(orderMap.get("pageNo"))),Constant.DEFAULT_PAGE_SIZE);
        List<Order> orderList = orderMapper.findUndonePageByParam(orderMap);
        PageInfo<Order> pageInfo =  new PageInfo<>(orderList);

        return pageInfo;
    }

    /**
     * 根据订单Id查询订单
     *
     * @param id
     * @return
     */
    @Override
    public Order findOrderById(Integer id)throws ServiceException {
        Order order = orderMapper.findOrderWithCustomerById(id);
        if(order == null){
            throw new ServiceException("参数异常或者订单不存在");
        }
        return order;
    }

    /**
     * 根据订单Id查询服务类型
     *
     * @param id
     * @return
     */
    @Override
    public ServiceType findServiceTypeByOrderId(Integer id) {

        return serviceTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据订单id删除订单
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delOrderByOrderId(Integer id) {

        //1.删除配件的关联关系表。
        List<OrderParts> orderPartsList = orderPartsMapper.findByOrderId(id);
                if(orderPartsList !=null && orderPartsList.size() >0){
                for(OrderParts orderParts : orderPartsList){
                    orderPartsMapper.deleteByPrimaryKey(orderParts.getId());
                }
        }
             //4.根据车辆ID查找用户信息
            Car car  = carMapper.findcarByOrderId(id);
             Customer customer = customerMapper.selectByPrimaryKey(car.getCustomerId());
            //3.删除车辆信息
            //5.查看用户对应的是否还有车辆信息，如果没有删除用户
             List<Car> carList = carMapper.findCarByCustomerId(customer.getId());
             System.out.println(carList);
             if(carList == null && carList.size() == 0){
                customerMapper.deleteByPrimaryKey(customer.getId());
             }
            carMapper.deleteByPrimaryKey(car.getId());
            //2.删除订单，
                orderMapper.deleteByPrimaryKey(id);

    }

    /**
     * 更新订单
     *
     * @param orderVo
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editOrder(OrderVo orderVo) throws ServiceException {
        Order order = orderMapper.selectByPrimaryKey(orderVo.getId());
        if(order == null) {
            throw new ServiceException("参数异常或者订单不存在");
        }
            order.setOrderMoney(orderVo.getFee());
            order.setCarId(orderVo.getCarId());
            order.setServiceTypeId(orderVo.getServiceTypeId());
            orderMapper.updateByPrimaryKeySelective(order);

        /*删除原有的关联关系*/
        OrderPartsExample orderPartsExample = new OrderPartsExample();
        orderPartsExample.createCriteria().andOrderIdEqualTo(orderVo.getId());
        orderPartsMapper.deleteByExample(orderPartsExample);

        /*新增关联关系*/
        List<PartsVo> partsVoList = orderVo.getPartsList();
        addOrderParts(order.getId(),partsVoList);

    }

    /**
     * 下发订单
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void transOrder(Integer id)throws ServiceException {
        //1.查询订单
        Order order = orderMapper.selectByPrimaryKey(id);
        System.out.println(order);
         //2.判断订单是否为空
        if(order == null){
            throw new ServiceException("参数异常或订单不存在");
        }
        //3.判断订单的状态是否为待修
        if(!order.getState().equals(Order.ORDER_STATE_NEW)){
            throw new ServiceException(("该订单已经生成并下发，操作失败"));
        }
        System.out.println("111"+order.getState());
        order.setState(Order.ORDER_STATE_TRANS);
        System.out.println("222"+order.getState());
        orderMapper.updateByPrimaryKeySelective(order);


        /*存入队列*/

        sendOrderInfoToMq(id);

    }


    /**
     * 根据维修部门领取任务，解析json数据，修改前台订单状态
     *
     * @param json
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void editState(String json) {
        OrderStateDto orderStateDto = new Gson().fromJson(json,OrderStateDto.class);
        //根据订单ID获取订单
        Order order = orderMapper.selectByPrimaryKey(orderStateDto.getOrderId());
        //判断该订单是否存在
        if(order == null){
            logger.info("{}==>订单不存在 ",orderStateDto.getOrderId());
        }

        //如果订单存在，修改订单状态，
        order.setState(orderStateDto.getState());
        orderMapper.updateByPrimaryKeySelective(order);


        //新增订单操作员
        //判断订单操作员ID是否为Null，
        //如果不为null说明操作员领取任务，进行添加订单操作员
        //若果为null说明完成订单，

        if(orderStateDto.getEmployeeId() != null){

            OrderEmployee orderEmployee = new OrderEmployee();

            orderEmployee.setEmployeeId(orderStateDto.getEmployeeId());
            orderEmployee.setOrderId(orderStateDto.getOrderId());
            orderEmployeeMapper.insertSelective(orderEmployee);
        }


    }

    /*==========================维修组===================================*/

    public  void sendOrderInfoToMq(Integer id){
        //获取订单的详细信息
        Order order = orderMapper.findOrderWithCustomerById(id);
        //获取订单的服务信息
        ServiceType serviceType = serviceTypeMapper.selectByPrimaryKey(order.getServiceTypeId());
        //获取订单配件
        List<Parts> partsList  =partsMapper.findPartaByOrderId(id);

        for(Parts parts : partsList){
            System.out.println("配件类型22222"+ parts.getType().getTypeName());
            System.out.println(parts.getNum());
        }




        OrderDto orderDto = new OrderDto();
        orderDto.setOrder(order);
        orderDto.setServiceType(serviceType);
        orderDto.setPartsList(partsList);


        //封装json
        String json = new Gson().toJson(orderDto);

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                return session.createTextMessage(json);
            }
        });

    }





    /*==============================================================================*/

    /*添加订单的关联关系*/
    private void addOrderParts(Integer orderId,List<PartsVo> partsVoList){
        if(partsVoList !=null && partsVoList.size()>0){
            for(PartsVo partsVo : partsVoList){
            OrderParts orderParts = new OrderParts();
                orderParts.setOrderId(orderId);
                orderParts.setPartsId(partsVo.getId());
                orderParts.setNum(partsVo.getNum());
                orderPartsMapper.insertSelective(orderParts);
            }
        }

    }






}
