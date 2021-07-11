package com.supkingx.base.b_singleton.test;

import com.supkingx.base.b_singleton.Singleton4;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Test4 {
    public static void main(String[] args) {
        Singleton4 singleton = Singleton4.getSingleton();
        Singleton4 singleton2 = Singleton4.getSingleton();
        System.out.println(singleton);
        System.out.println(singleton2);

    }
}
