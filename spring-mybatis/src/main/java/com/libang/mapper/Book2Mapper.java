package com.libang.mapper;

import com.libang.entity.Book2;
import com.libang.entity.Book2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Book2Mapper {
    long countByExample(Book2Example example);

    int deleteByExample(Book2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Book2 record);

    int insertSelective(Book2 record);

    List<Book2> selectByExample(Book2Example example);

    Book2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Book2 record, @Param("example") Book2Example example);

    int updateByExample(@Param("record") Book2 record, @Param("example") Book2Example example);

    int updateByPrimaryKeySelective(Book2 record);

    int updateByPrimaryKey(Book2 record);
}