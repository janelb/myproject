package com.libang.erp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Type;
import com.libang.erp.entity.TypeExample;
import com.libang.erp.mapper.TypeMapper;
import com.libang.erp.service.TypeService;
import com.libang.erp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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

    /**
     * 修改类型
     *
     * @param type
     * @return
     */
    @Override
    public Integer update(Type type) {

        Integer res = typeMapper.updateByPrimaryKeySelective(type);
        return res;
    }

    /**
     * 通过pageNo进行分类
     *
     * @param pageNo
     * @param queryMap
     * @return
     */
    @Override
    public PageInfo findPageAndByQueryMap(Integer pageNo, Map<String, Object> queryMap) {

        PageHelper.startPage(pageNo ,Constant.DEFAULT_PAGE_SIZE);
        List<Type> typeList = typeMapper.findPage();
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        return pageInfo;
    }
}
