package com.supkingx.base.h_proxy.dynamicproxy;

/**
 * @description: 被代理类
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Superman implements Human {
    @Override
    public String getBelief() {
        return "I believe I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("I like eating " + food);
    }
}
