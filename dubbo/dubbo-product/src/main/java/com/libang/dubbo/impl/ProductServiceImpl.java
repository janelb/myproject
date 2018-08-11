package com.libang.dubbo.impl;

import com.libang.dubbo.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author libang
 * @date 2018/8/11 11:21
 */
@Service
public class ProductServiceImpl implements ProductService {

    public String findById(Integer id) {

        if(id.equals("1001")){
            return "Bob";
        }else{

            return "tom";
        }

    }
}
