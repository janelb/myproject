package com.libang.service.impl;


import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.libang.entity.Parts;
import com.libang.entity.Type;
import com.libang.entity.TypeExample;
import com.libang.mapper.PartsMapper;
import com.libang.mapper.TypeMapper;
import com.libang.service.PartService;
import com.libang.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/7/23 21:20
 */
@Service
public class PartServiceImpl implements PartService {
    /*private Logger logger = LoggerFactory.getLogger(PartServiceImpl.class);*/

    @Autowired
    private PartsMapper partsMapper;
    @Autowired
    private TypeMapper typeMapper;

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
     * 新增配件类型
     *
     * @param parts
     */
    @Override
    public void save(Parts parts) {
        partsMapper.insertSelective(parts);
        int id = parts.getId();

      /*  logger.debug("新增配件:{}",id);*/

    }

    /**
     * 跟新
     *
     * @param parts
     */
    @Override
    public void edit(Parts parts) {
        partsMapper.updateByPrimaryKeySelective(parts);
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
     * @param pageNo
     */
    @Override
    public Parts findByPageNo(String pageNo) {

       Parts parts= partsMapper.findByPageNo(pageNo);

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


}
