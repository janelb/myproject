package com.libang.erp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.PartsIn;
import com.libang.erp.entity.PartsStream;
import com.libang.erp.mapper.PartsInMapper;
import com.libang.erp.mapper.PartsStreamMapper;
import com.libang.erp.service.PartsStreamService;
import com.libang.erp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author libang
 * @date 2018/8/10 15:21
 */
@Service
public class PartsStreamServiceImpl implements PartsStreamService {

    @Autowired
    private PartsStreamMapper partsStreamMapper;

    @Autowired
    private PartsInMapper partsInMapper;
    /**
     * 查看仓库出库配件流水
     *
     * @param queryMap
     * @return
     */
    @Override
    public PageInfo<PartsStream> findPartsOutInventory(Map<String, Object> queryMap) {

        PageHelper.startPage(Integer.parseInt(String.valueOf(queryMap.get("pageNo"))),Constant.DEFAULT_PAGE_SIZE);
        List<PartsStream> partsStreamLIst =  partsStreamMapper.findPartsOutInventory(queryMap);
        PageInfo<PartsStream> pageInfo = new PageInfo<>(partsStreamLIst);

        return pageInfo;
    }

    /**
     * 查看仓库配件入库流水
     *
     * @param inMap
     * @return
     */
    @Override
    public PageInfo<PartsIn> findPartsInInventory(Map<String, Object> inMap) {
        PageHelper.startPage(Integer.parseInt(String.valueOf(inMap.get("pageNo"))),Constant.DEFAULT_PAGE_SIZE);
        List<PartsIn> partsIns = partsInMapper.findPartsInInventory(inMap);
        PageInfo<PartsIn> partsInPageInfo = new PageInfo<>(partsIns);

        return partsInPageInfo;
    }
}
