package com.supkingx.base.k_lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 实现一个自旋锁
 * @Author: wangchao
 * @Date: 2021/7/22
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 获取锁
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "--myLock,come in");

        // 自旋获取锁
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(thread.getName() + "--myLock,get");
    }

    /**
     * 释放锁
     */
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "--myUnLock()");

    }
}
