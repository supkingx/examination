package com.supkingx.base.i_proxy.dynamicproxy.javaproxy;

import com.supkingx.base.i_proxy.dynamicproxy.Human;
import com.supkingx.base.i_proxy.dynamicproxy.Superman;
import com.supkingx.base.i_proxy.staticproxy.ClothFactory;
import com.supkingx.base.i_proxy.staticproxy.SupClothFactory;

/**
 * @description: 动态代理
 * 想要实现动态代理，需要解决的问题：
 * 1、如何根据加载到内存中的被代理类，动态创建一个代理类及其对象
 * 2、当通过代理类的对象调用方法时，如何动态的去调用被代理类中的同名方法。
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        // 被代理类对象
        Superman superman = new Superman();
        // 注意：这里的human不是superman，因为我们是使用superman在这里是被代理类，
        // 通过ProxyFactory.getProxyInstance(superman)生成了superman的代理对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superman);
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("fish");

        System.out.println("\n----------------------\n");

        // 之前的静态代理，我们也可以通过动态代理来创建SupClothFactory的代理对象
        SupClothFactory supClothFactory = new SupClothFactory();
        ClothFactory clothFactory = (ClothFactory)ProxyFactory.getProxyInstance(supClothFactory);
        clothFactory.produceCloth();
    }
}
