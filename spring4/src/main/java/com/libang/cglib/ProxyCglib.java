package com.libang.cglib;

/**
 * @author libang
 * @date 2018/7/16 10:55
 */
public class ProxyCglib extends Sale {

    @Override
    public void salePc() {
       /* System.out.println("售前服务....");*/
        super.salePc();
     /*   System.out.println("售后服务....");*/
    }
}
