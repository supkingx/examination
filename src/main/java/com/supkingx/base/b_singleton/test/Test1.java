package com.supkingx.base.b_singleton.test;

import com.supkingx.base.b_singleton.Singleton1;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Test1 {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.SINGLETON;
        System.out.println(singleton1);
    }
}
