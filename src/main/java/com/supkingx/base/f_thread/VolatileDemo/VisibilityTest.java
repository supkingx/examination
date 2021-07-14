package com.supkingx.base.f_thread.VolatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @description: 验证volatile的可见性
 * 线程"oneThread"对资源Data进行了值的修改，但是main线程并知道这件事情
 * @Author: wangchao
 * @Date: 2021/7/14
 */
public class VisibilityTest {
    public static void main(String[] args) {
        Data data = new Data(); // 共享资源
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-hello");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.growUp();
            System.out.println(Thread.currentThread().getName() + "-grow up:" + data.age);
        }, "oneThread").start();

        // 监听age值的是否被修改，如果一直是原值10，则继续循环
//        while (data.age == 10) {
//
//        }
        // age被修改后跳出循环并输出
        System.out.println(Thread.currentThread().getName() + ":" + data.age);
    }
}
