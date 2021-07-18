package com.supkingx.base.i_proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object obj;// 需要使用被代理类的对象进行赋值

    public void bind(Object o) {
        this.obj = o;
    }

    // 当我们通过代理类的对象，调用方法A时，就会自动的调用如下方法：invoke(0
    // 将被代理类要执行的方法a的功能，声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在原方法执行执行之前加入方法
        System.out.println("method.invoke(obj, args)，执行之前");

        // 代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        // obj:被代理对象
        Object invoke = method.invoke(obj, args);

        // 在原方法执行执行之后加入方法
        System.out.println("method.invoke(obj, args)，执行之后");

        // 上诉方法的返回值就作为当前类中的invoke()的返回值
        return invoke;
    }
}
