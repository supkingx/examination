package com.supkingx.base.b_singleton;

/**
 * @description:
 * 饿汉式：直接创建，不管你需不需要
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Singleton1 {
    public static final Singleton1 SINGLETON = new Singleton1();

    private Singleton1() {
    }
}
