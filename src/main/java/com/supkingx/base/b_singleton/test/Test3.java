package com.supkingx.base.b_singleton.test;

import com.supkingx.base.b_singleton.Singleton3;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Test3 {
    public static void main(String[] args) {
        Singleton3 singleton3 = Singleton3.SINGLETON;
        System.out.println(singleton3.getInfo());
    }
}
