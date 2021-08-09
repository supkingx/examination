package com.supkingx.base.f_thread.VolatileDemo;

/**
 * @description: 验证volatile的原子性
 * @Author: wangchao
 * @Date: 2021/7/14
 */

public class AtomicityTest {
    public static void main(String[] args) {
        Data data = new Data();

        // 20个线程
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.addPlusPlus();
                    data.addAtomic();
                }
            }, "Thread-" + i).start();
        }

        // 默认有两个线程，一个是main线程，一个是GC线程
        while (Thread.activeCount() > 2) {
            // 使main线程由执行态变成就绪态，让出cpu时间，在下一个线程执行的时候，此线程有可能被执行，也有可能不被执行
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "-" + data.age);
        System.out.println(Thread.currentThread().getName() + "-" + data.num);
    }
}
