package com.libang.erp.mapper;

import com.libang.erp.entity.OrderParts;
import com.libang.erp.entity.OrderPartsExample;
import java.util.List;

import com.libang.erp.entity.Parts;
import org.apache.ibatis.annotations.Param;

public interface OrderPartsMapper {
    long countByExample(OrderPartsExample example);

    int deleteByExample(OrderPartsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderParts record);

    int insertSelective(OrderParts record);

    List<OrderParts> selectByExample(OrderPartsExample example);

    OrderParts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderParts record, @Param("example") OrderPartsExample example);

    int updateByExample(@Param("record") OrderParts record, @Param("example") OrderPartsExample example);

    int updateByPrimaryKeySelective(OrderParts record);

    int updateByPrimaryKey(OrderParts record);


    List<OrderParts> findByOrderId(Integer id);
}