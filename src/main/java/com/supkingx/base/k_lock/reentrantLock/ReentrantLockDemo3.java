package com.supkingx.base.k_lock.reentrantLock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/8/11
 */
public class ReentrantLockDemo3 {
    static Object object = new Object();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            m1();
        }
    }

    public static void m1() {
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t" + "-------外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "--------中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t----------内层调用");
                    }
                }
            }
        }, "t1").start();
    }
}
