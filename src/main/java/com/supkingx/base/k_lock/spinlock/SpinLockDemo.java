package com.supkingx.base.k_lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 实现一个自旋锁
 * @Author: wangchao
 * @Date: 2021/7/22
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "--myLock,come in");

        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "--myUnLock()");

    }
}
