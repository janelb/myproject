package com.libang.erp.mapper;

import com.libang.erp.entity.OrderCount;
import com.libang.erp.entity.OrderCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderCountMapper {
    long countByExample(OrderCountExample example);

    int deleteByExample(OrderCountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderCount record);

    int insertSelective(OrderCount record);

    List<OrderCount> selectByExample(OrderCountExample example);

    OrderCount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderCount record, @Param("example") OrderCountExample example);

    int updateByExample(@Param("record") OrderCount record, @Param("example") OrderCountExample example);

    int updateByPrimaryKeySelective(OrderCount record);

    int updateByPrimaryKey(OrderCount record);
}