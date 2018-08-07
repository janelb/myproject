package com.libang.erp.service;

import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Employee;
import com.libang.erp.entity.Order;
import com.libang.erp.entity.ServiceType;
import com.libang.erp.entity.Type;
import com.libang.erp.exception.ServiceException;
import com.libang.erp.vo.OrderVo;


import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/8/3 20:35
 */
public interface OrderService {

    /**
     * 查询所有服务类型列表
     * @return
     */
    List<ServiceType> findAllServiceType();

    /**
     * 查询所有【配件类型
     * @return
     */
    List<Type> findAllPartsType();


    /**
     * 保存订单
     * @param orderVo
     */
    void saveOrder(OrderVo orderVo,Employee employee);


    /**
     * 查询所有订单
     * @param orderMap
     * @return
     */
    List<Order> findAllOrder(Map<String,Object> orderMap);

    /**
     * 根据条件进行查询订单列表
     * @param orderMap
     * @return
     */
    PageInfo<Order> findPageByParam(Map<String,Object> orderMap);


    /**
     * 根据订单Id查询订单
     * @param id
     * @return
     */
    Order findOrderById(Integer id) throws ServiceException;

    /**
     *
     * 根据订单Id查询服务类型
     * @param id
     * @return
     */
    ServiceType findServiceTypeByOrderId(Integer id);

    /**
     *
     * 根据订单id删除订单
     * @param id
     */
    void delOrderByOrderId(Integer id);

    /**
     * 更新订单
     * @param orderVo
     */
    void editOrder(OrderVo orderVo) throws ServiceException;

    /**
     * 下发订单
     * @param id
     */
    void transOrder(Integer id) throws ServiceException;
}
