package com.libang.service.impl;

import com.libang.entity.Parts;
import com.libang.mapper.PartsMapper;
import com.libang.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author libang
 * @date 2018/7/23 21:20
 */
@Service
public class PartServiceImpl implements PartService {
    @Autowired
    private PartsMapper partsMapper;

    /**
     * 根据id进行查找
     *
     * @param id
     * @return
     */
    @Override
    public Parts findById(Integer id) {

        return partsMapper.selectByPrimaryKey(id);
    }
}
