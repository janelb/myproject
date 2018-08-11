package com.libang.erp.service;

import com.github.pagehelper.PageInfo;
import com.libang.erp.entity.PartsIn;
import com.libang.erp.entity.PartsStream;

import java.util.Map;

/**
 * @author libang
 * @date 2018/8/10 15:21
 */
public interface PartsStreamService {
    /**
     * 查看仓库出库配件流水
     * @param queryMap
     * @return
     */
    PageInfo<PartsStream> findPartsOutInventory(Map<String,Object> queryMap);

    /**
     * 查看仓库配件入库流水
     * @param inMap
     * @return
     */
    PageInfo<PartsIn> findPartsInInventory(Map<String,Object> inMap);
}
