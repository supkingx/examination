package com.supkingx.base.h_proxy.staticproxy;

/**
 * @description: 静态代理举例
 * 代理类和被代理类在编译期间就确定下来了
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        // 创建被代理对象
        SupClothFactory supClothFactory = new SupClothFactory();
        // 创建代理类对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(supClothFactory);
        proxyClothFactory.produceCloth();
    }
}
