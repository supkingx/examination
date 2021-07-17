package com.supkingx.base.g_cas.AtomicDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * cas 是什么，compareAndSet
 * @Author: wangchao
 * @Date: 2021/7/16
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(0);
        // 先比较，如果是i等于期望值0，则将其置为2021
        System.out.println(i.compareAndSet(0,2021));

        System.out.println(i.getAndIncrement());

    }
}
