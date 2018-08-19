package com.libang.erp.mapper;

import com.libang.erp.entity.OrderTimeOut;
import com.libang.erp.entity.OrderTimeOutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderTimeOutMapper {
    long countByExample(OrderTimeOutExample example);

    int deleteByExample(OrderTimeOutExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderTimeOut record);

    int insertSelective(OrderTimeOut record);

    List<OrderTimeOut> selectByExample(OrderTimeOutExample example);

    OrderTimeOut selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderTimeOut record, @Param("example") OrderTimeOutExample example);

    int updateByExample(@Param("record") OrderTimeOut record, @Param("example") OrderTimeOutExample example);

    int updateByPrimaryKeySelective(OrderTimeOut record);

    int updateByPrimaryKey(OrderTimeOut record);
}