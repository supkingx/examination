package com.supkingx.base.h_proxy.staticproxy;

/**
 * @description: 静态代理类
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class ProxyClothFactory implements ClothFactory{

    private ClothFactory clothFactory; // 就拿被代理对象进行实例化

    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");

        clothFactory.produceCloth();

        System.out.println("代理工厂做一些后续的收尾工作");
    }
}
