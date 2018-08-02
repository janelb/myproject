package com.libang.erp.mapper;


import com.libang.erp.entity.Type;
import com.libang.erp.entity.TypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TypeMapper {
    long countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    Type findByName(String typeName);

    /*进行分页*/

    List<Type> findPage();
}