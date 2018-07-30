package com.libang.mapper;

import com.libang.entity.Parts;
import com.libang.entity.PartsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import javax.servlet.http.Part;

public interface PartsMapper {
    long countByExample(PartsExample example);

    int deleteByExample(PartsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Parts record);

    int insertSelective(Parts record);

    List<Parts> selectByExample(PartsExample example);

    Parts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Parts record, @Param("example") PartsExample example);

    int updateByExample(@Param("record") Parts record, @Param("example") PartsExample example);

    int updateByPrimaryKeySelective(Parts record);

    int updateByPrimaryKey(Parts record);





    List<Parts> findPageWithType();


    /**
     *
     * 根据
     * @param queryMap
     * @return
     */
    List<Parts> findPageAndQueryMap(Map<String,Object> queryMap);

    /**
     * 按数量进行筛选
     * @param queryMap
     * @return
     */
    List<Parts> findPageAndQueryMapAndNumber(Map<String,Object> queryMap);

    /**
     * 根据pageNo进行查找
     * @param pageNo
     * @return
     */
    Parts findByPageNo(String pageNo);


    List<Parts> findBYTypeId(Integer id);
}