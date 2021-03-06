package com.libang.erp.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.google.gson.Gson;
import com.libang.erp.entity.*;
import com.libang.erp.mapper.PartsInMapper;
import com.libang.erp.mapper.PartsMapper;
import com.libang.erp.mapper.PartsStreamMapper;
import com.libang.erp.mapper.TypeMapper;
import com.libang.erp.service.PartService;
import com.libang.erp.util.Constant;
import com.libang.erp.vo.FixOrderPartsVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/23 21:20
 */
@Service
public class PartServiceImpl implements PartService {
    private Logger logger = LoggerFactory.getLogger(PartServiceImpl.class);

    @Autowired
    private PartsMapper partsMapper;
    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private PartsStreamMapper partsStreamMapper;

    @Autowired
    private PartsInMapper partsInMapper;

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


    /**
     * 根据页码获取页数集合
     *根据关键字查询
     * @param pageNo 页码 1,2
     * @return pageInfo对象
     */
    @Override
    public PageInfo<Parts> findPage(Integer pageNo) {
        //进行分页
        PageHelper.startPage(pageNo, Constant.DEFAULT_PAGE_SIZE);

        //进行排序
   /*     PartsExample partsExample = new PartsExample();
       *//* partsExample.setOrderByClause("id desc");*/
       /* List<Parts> partsList = partsMapper.selectByExample();*/

        List<Parts> partsList = partsMapper.findPageWithType();
        //进行分装对象
        PageInfo<Parts> pageInfo = new PageInfo<>(partsList);

        return pageInfo;
    }





    /**
     * 查询所有type
     *
     * @return
     */
    @Override
    public List<Type> findByTypeList() {
        TypeExample typeExample = new TypeExample();
        return typeMapper.selectByExample(typeExample);
    }

    /**
     * 通过page和queryMap进行查询
     *
     * @param pageNo
     * @param queryMap
     * @return
     */
    @Override
    public PageInfo<Parts> findPageAndByQueryMap(Integer pageNo, Map<String, Object> queryMap) {

            PageHelper.startPage(pageNo,Constant.DEFAULT_PAGE_SIZE);

            List<Parts> partsList = partsMapper.findPageAndQueryMap(queryMap);
            PageInfo<Parts> pageInfo = new PageInfo<>(partsList);

            return pageInfo;
    }

    /**
     * 根据Id进行删除
     *
     * @param id
     */
    @Override
    public void delById(Integer id) {
        Parts parts = partsMapper.selectByPrimaryKey(id);
        if(parts != null){
              partsMapper.deleteByPrimaryKey(id);
        }
       /*logger.debug("删除配件:{}",parts);*/
    }

    /**
     * 新增配件
     *
     * @param parts
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(Parts parts) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();

        partsMapper.insertSelective(parts);
        int id = parts.getId();

        //  配件入库记录入库流水
        PartsIn partsIn  = new PartsIn();
        partsIn.setPartsId(parts.getId());
        partsIn.setEmployeeId(employee.getId());
        partsIn.setNum(parts.getInventory());

        partsInMapper.insertSelective(partsIn);


      /*  logger.debug("新增配件:{}",id);*/

    }

    /**
     * 更新
     * @param parts
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void edit(Parts parts) {
        partsMapper.updateByPrimaryKeySelective(parts);

        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        //根据配件id在数据库查询更改前的数量
        Parts parts1 = partsMapper.selectByPrimaryKey(parts.getId());

        //跟新配件流水
        PartsIn partsIn  = new PartsIn();
        partsIn.setPartsId(parts.getId());
        partsIn.setEmployeeId(employee.getId());

        //如果配件数量发生更改，更改后的数量-更改前的数量=变化的数量
        //如果为正则说明添加配件，如果负数说明减少该配件
        partsIn.setNum(parts.getInventory() -  parts1.getInventory());

        partsInMapper.insertSelective(partsIn);


    }

    /**
     * 通过page和queryMap进行查询和number
     *
     * @param pageNo
     * @param queryMap
     * @return
     */
    @Override
    public PageInfo<Parts> findPageAndByQueryMapAndNumber(Integer pageNo, Map<String, Object> queryMap) {

        PageHelper.startPage(pageNo,Constant.DEFAULT_PAGE_SIZE);

        List<Parts> partsList = partsMapper.findPageAndQueryMapAndNumber(queryMap);
        PageInfo<Parts> pageInfo = new PageInfo<>(partsList);

        return pageInfo;


    }

    /**
     * 检查编码
     *
     * @param partsNo
     */
    @Override
    public Parts findByPartsNo(String partsNo) {

       Parts parts= partsMapper.findByPartsNo(partsNo);

        return parts;
    }

    /**
     * 根据TypeId进行查找
     *
     * @param id
     * @return
     */
    @Override
    public List<Parts> findByTypeId(Integer id) {

        List<Parts> partsList = partsMapper.findBYTypeId(id);
        return partsList;
    }

    /**
     * 根据订单ID查询配件列表
     *
     * @param id
     * @return
     */
    @Override
    public List<Parts> findPartsByOrderId(Integer id) {

        List<Parts> partsList = partsMapper.findPartaByOrderId(id);

        return partsList;
    }



    /**
     * 从消息队列中获取数据并解析json数据
     * 减少库存
     *
     * @param json
     */
    @Override
    public void subInventory(String json) {
            //解析json数据
        FixOrderPartsVo fixOrderPartsVo = new Gson().fromJson(json,FixOrderPartsVo.class);
        //根据员工id和配件集合减少库存
        for(FixOrderParts fixOrderParts : fixOrderPartsVo.getFixOrderPartsList()){
            Parts parts = partsMapper.selectByPrimaryKey(fixOrderParts.getPartsId());
            parts.setInventory(parts.getInventory() - fixOrderParts.getPartsNum());
            partsMapper.updateByPrimaryKeySelective(parts);

            //生成出库流水
            PartsStream partsStream = new PartsStream();
            partsStream.setOrderId(fixOrderParts.getOrderId());
            partsStream.setPartsId(fixOrderParts.getPartsId());
            partsStream.setEmployeeId(fixOrderPartsVo.getEmployeeId());
            partsStream.setNum(fixOrderParts.getPartsNum());
            partsStream.setType(PartsStream.PARTS_STREAM_TYPE_OUT);

            partsStreamMapper.insertSelective(partsStream);
            logger.info("{}配件出库",partsStream);
        }




    }


}
