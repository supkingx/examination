package com.supkingx.base.f_thread.VolatileDemo;

/**
 * @description: 加了volatile避免指令重排，synchronized保证了原子性，完美
 * @Author: wangchao
 * @Date: 2021/7/16
 */
public class SingletonDemo3 {
    // 避免指令重排
    private static volatile SingletonDemo3 singletonDemo = null;

    private SingletonDemo3() {
        // 对象常见的时候，构造器执行
        System.out.println(Thread.currentThread().getName() + "-我是构造器!");
    }

    public static SingletonDemo3 getSingletonDemo() {
        if (null == singletonDemo) {
            synchronized (SingletonDemo3.class) {
                // 作次判断是为了只创建一次
                if (null == singletonDemo) {
                    singletonDemo = new SingletonDemo3();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        // 多线程情况下，一切皆有肯
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                SingletonDemo3.getSingletonDemo();
            }).start();
        }
    }
}
