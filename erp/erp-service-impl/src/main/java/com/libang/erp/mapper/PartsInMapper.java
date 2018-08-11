package com.libang.erp.mapper;

import com.libang.erp.entity.PartsIn;
import com.libang.erp.entity.PartsInExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PartsInMapper {
    long countByExample(PartsInExample example);

    int deleteByExample(PartsInExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PartsIn record);

    int insertSelective(PartsIn record);

    List<PartsIn> selectByExample(PartsInExample example);

    PartsIn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PartsIn record, @Param("example") PartsInExample example);

    int updateByExample(@Param("record") PartsIn record, @Param("example") PartsInExample example);

    int updateByPrimaryKeySelective(PartsIn record);

    int updateByPrimaryKey(PartsIn record);

    List<PartsIn> findPartsInInventory(Map<String,Object> inMap);
}