package com.supkingx.base.i_proxy.staticproxy;

/**
 * @description: 被代理类
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class SupClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("sup生产衣服、。。。。。。。。");
    }
}
