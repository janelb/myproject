package com.libang.service.impl;

import com.libang.entity.Type;
import com.libang.entity.TypeExample;
import com.libang.mapper.TypeMapper;
import com.libang.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author libang
 * @date 2018/7/25 15:06
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询所有类型
     *
     * @return
     */
    @Override
    public List<Type> findAll() {
        TypeExample typeExample = new TypeExample();
        List<Type> typeList = typeMapper.selectByExample(typeExample);
        return typeList;
    }

    /**
     * 新增类型
     *
     * @param type
     */
    @Override
    public Integer save(Type type) {
       Integer res = typeMapper.insertSelective(type);
        return res;
    }

    /**
     * 通过名称查找
     *
     * @param typeName
     * @return
     */
    @Override
    public Type findByName(String typeName) {
        Type type = typeMapper.findByName(typeName);
        return type;
    }

    /**
     * 根据Id进行删除
     *
     * @param id
     */
    @Override
    public void delById(Integer id) {
        typeMapper.deleteByPrimaryKey(id);
    }
}
