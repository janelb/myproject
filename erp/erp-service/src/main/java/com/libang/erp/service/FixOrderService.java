package com.libang.erp.service;

import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Employee;
import com.libang.erp.entity.FixOrder;
import com.libang.erp.entity.Order;

import java.util.Map;

/**
 * @author libang
 * @date 2018/8/9 10:16
 */
public interface FixOrderService {

    /**
     *
     *封装订单的json对象，
     * @param json
     */
    void createFixOrder(String json);

    /**
     * 查询维修订单详情
     * @param orderMap
     * @return
     */
    PageInfo<FixOrder> findPageByParam(Map<String,Object> orderMap);

    /**
     * 领取任务根据订单id,和登录人员跟新订单状态，和记住该订单的维修人员
     * @param id
     * @param employee
     */
    void taskReceive(Integer id, Employee employee);

    /**
     * 根据订单Id查询订单
     * @param id
     * @return
     */
    FixOrder getFixOrderId(Integer id);

    /**
     *完成检修
     * 根据订单Id进行跟新数据库
     * @param id
     */
    void taskDone(Integer id);

    /**
     * 领取检修任务根据订单id,和登录人员更新订单状态，和记住该订单的检修人员
     * @param id
     * @param employee
     */
    void taskReceiveCheck(Integer id, Employee employee);

    /**
     *根据订单Id修改数据库订单的质检状态
     * @param id
     */
    void taskDoneCheck(Integer id);

    /**
     *
     * 记录订单超时次数
     * @param jobName
     */
    void addOrderTimeOut(String jobName);
}
