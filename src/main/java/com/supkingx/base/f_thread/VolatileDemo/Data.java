package com.supkingx.base.f_thread.VolatileDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/14
 */
public class Data {
    // 加了volatile后即可保证可见性
    volatile int age = 10;
//    int age = 10;


    public void growUp() {
        this.age = 18;
    }

    // 此时age加了volatile 关键字，volatile不保证原子性
    public void addPlusPlus() {
        age++;
    }

    // 此时age加了volatile 关键字，volatile不保证原子性,但可以用synchronized实现原子性
//    public synchronized void addPlusPlus() {
//        age++;
//    }

    AtomicInteger num = new AtomicInteger(0);
    public void addAtomic(){
        num.getAndIncrement();
    }
}
