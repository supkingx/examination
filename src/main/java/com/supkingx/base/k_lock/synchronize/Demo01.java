package com.supkingx.base.k_lock.synchronize;

/**
 * @description:
 * * 去掉synchronized后，wait/notify会报错
 * <p>
 * 将notify放在wait前面，wait的程序无法顺利执行
 * @Author: wangchao
 * @Date: 2021/8/12
 */
public class Demo01 {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "\t我来了");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t被叫醒");

            }
        }, "AA").start();

        new Thread(() -> {
            synchronized (o) {
                o.notify();
                System.out.println(Thread.currentThread().getName() + "\t叫醒你");
            }
        }, "BB").start();
    }
}
