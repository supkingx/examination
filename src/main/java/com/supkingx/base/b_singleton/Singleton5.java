package com.supkingx.base.b_singleton;

/**
 * @description: 懒汉式
 * 线程安全（适用于多线程）
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Singleton5 {
    private static Singleton5 singleton;

    private Singleton5() {

    }

    public static Singleton5 getSingleton() {
        // 外层的这个判断完全是为了性能考虑
        if (singleton == null) {
            // 与Singleton4线程不安全锁区别，加了synchronized后线程安全
            synchronized (Singleton5.class) {
                if (singleton == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    singleton = new Singleton5();
                }
            }
        }
        return singleton;
    }
}
