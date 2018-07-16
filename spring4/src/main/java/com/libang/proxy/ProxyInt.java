package com.libang.proxy;

/**
 * @author libang
 * @date 2018/7/14 19:19
 */
public class ProxyInt implements Sale {

    private Sale sale;
    public ProxyInt(Sale sale){
        this.sale=sale;
    }

    @Override
    public void salePc() {
        System.out.println("加价1000");
        sale.salePc();
        System.out.println("销售鼠标200.。");
        System.out.println("抽奖400元");
    }

    @Override
    public int salePrice() {
        return 100;
    }
}
