package com.libang.erp.service;


import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.Parts;
import com.libang.erp.entity.Type;

import java.util.List;
import java.util.Map;

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


    /**
     * 根据页码获取页数集合
     * @param pageNo 页码 1,2
     * @return pageInfo对象
     */
    PageInfo<Parts> findPage(Integer pageNo);





    /**
     * 查询所有type
     * @return
     */
    List<Type> findByTypeList();


    /**
     * 通过page和queryMap进行查询
     * @param pageNo
     * @param queryMap
     * @return
     */
    PageInfo<Parts> findPageAndByQueryMap(Integer pageNo, Map<String, Object> queryMap);

    /**
     * 根据Id进行删除
     * @param id
     */
    void delById(Integer id);

    /**
     * 新增配件类型
     * @param parts
     */
    void save(Parts parts);

    /**
     * 跟新
     * @param parts
     */
    void edit(Parts parts);

    /**
     * 通过page和queryMap进行查询和number
     * @param pageNo
     * @param queryMap
     * @return
     */
    PageInfo<Parts> findPageAndByQueryMapAndNumber(Integer pageNo, Map<String, Object> queryMap);


    /**
     * 检查编码
     * @param partsNo
     */
    Parts findByPartsNo(String partsNo);


    /**
     * 根据TypeId进行查找
     * @param id
     * @return
     */
    List<Parts> findByTypeId(Integer id);


    /**
     * 根据订单ID查询配件列表
     * @param id
     * @return
     */
    List<Parts> findPartsByOrderId(Integer id);

    /**
     *
     * 从消息队列中获取数据并解析json数据
     * @param json
     */
    void subInventory(String json);
}
