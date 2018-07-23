package com.libang.service;

import com.libang.entity.Parts;

/**
 * @author libang
 * @date 2018/7/23 21:01
 */
public interface PartService {

    /**
     * 根据id进行查找
     * @param id
     * @return
     */
    Parts findById(Integer id);
}
