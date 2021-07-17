package com.supkingx.base.f_thread.VolatileDemo;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/16
 */
public class SingletonDemo {
    private static SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        // 对象常见的时候，构造器执行
        System.out.println(Thread.currentThread().getName() + "-我是构造器!");
    }

    // 加synchronized可以解决线程不安全问题（增强可见性），但是太重了
    public static synchronized SingletonDemo getSingletonDemo() {
        if (null == singletonDemo) {
            singletonDemo = new SingletonDemo();
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        // 单线程环境下，只会创建一个对象，只会执行一次构造器
//        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());

        // 多线程情况下，一切皆有肯
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                SingletonDemo.getSingletonDemo();
            }).start();
        }

    }

}
