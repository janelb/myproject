package com.libang.mapper;

import com.libang.entity.Type;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/12 15:41
 */
public interface TypeMapper {

    List<Type> findByStudentId(Integer id);


}
