package com.supkingx.base.k_lock.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/21
 */
public class Phone2 implements Runnable {
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "---get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "---set()");
        } finally {
            lock.unlock();
        }
    }
}
