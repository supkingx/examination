package com.supkingx.base.i_proxy.dynamicproxy.cglib;

import com.supkingx.base.i_proxy.dynamicproxy.Superman;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/28
 */
public class CglibFactoryTest {
    public static void main(String[] args) {
        Object instanceProxy = CglibFactory.getInstanceProxy(new Superman());
    }
}
