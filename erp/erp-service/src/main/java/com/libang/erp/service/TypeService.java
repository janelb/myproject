package com.libang.erp.service;


import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Type;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/25 15:06
 */
public interface TypeService {

    /**
     * 查询所有类型
     * @return
     */
    List<Type> findAll();

    /**
     *
     * 新增类型
     * @param type
     */
    Integer save(Type type);


    /**
     * 通过名称查找
     * @param typeName
     * @return
     */
    Type findByName(String typeName);

    /**
     * 根据Id进行删除
     * @param id
     */
    void delById(Integer id);


    /**
     * 修改类型
     * @param type
     * @return
     */
    Integer update(Type type);

    /**
     * 通过pageNo进行分类
     * @param pageNo
     * @param queryMap
     * @return
     */
    PageInfo findPageAndByQueryMap(Integer pageNo, Map<String,Object> queryMap);
}
