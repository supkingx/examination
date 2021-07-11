package com.supkingx.base.b_singleton.test;

import com.supkingx.base.b_singleton.Singleton2;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Test2 {
    public static void main(String[] args) {
        Singleton2 singleton2 = Singleton2.SINGLETON;
        System.out.println(singleton2);
    }
}
