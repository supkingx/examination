package com.supkingx.base.i_proxy.dynamicproxy.javaproxy;

import java.lang.reflect.Proxy;

/**
 * @description: 使用java自带的代理功能
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class ProxyFactory {
    // 调用此方法，放回一个被代理类对象，被代理类的对象
    public static Object getProxyInstance(Object o) {
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(o);
        // 被代理类的 类加载器、接口、
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), handler);
    }
}
