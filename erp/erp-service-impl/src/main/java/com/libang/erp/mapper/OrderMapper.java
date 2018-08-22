package com.libang.erp.mapper;

import com.libang.erp.entity.Order;
import com.libang.erp.entity.OrderExample;
import java.util.List;
import java.util.Map;

import com.libang.erp.entity.Type;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    List<Order> findAllOrder(Map<String,Object> orderMap);

    List<Order> findUndonePageByParam(Map<String,Object> orderMap);

    Order findOrderWithCustomerById(Integer id);


    /**
     *
     * 根据订单Id查询配件类型列表
     * @param id
     * @return
     */
    List<Type> findTypeByOrderId(Integer id);

    /**
     * 根据时间和订单状态查找订单
     * @param state
     * @param dateTime
     * @return
     */
    List<Order> findDatilOrderByStateAndDateTime(@Param("state") String state, @Param("dateTime") String dateTime);
}