package com.supkingx.base.i_proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: 使用Cglib实现动态代理
 * @Author: wangchao
 * @Date: 2021/7/28
 */
public class CglibFactory {
    public static Object getInstanceProxy(Object obj){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("使用Cglib代理开始");
                Object invoke = methodProxy.invoke(obj, objects);
                return invoke;
            }
        });
        return enhancer.create();
    }
}
