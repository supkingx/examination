package com.supkingx.base.b_singleton;

/**
 * @description: 懒汉式
 * 线程不安全（适用于单线程）
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Singleton4 {
    private static Singleton4 singleton;

    private Singleton4() {

    }

    public static Singleton4 getSingleton() {
        if (singleton == null) {
            try {
                // 为了展示线程不安全，这是睡眠100ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton = new Singleton4();
        }
        return singleton;
    }
}
