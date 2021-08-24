package com.supkingx.base.k_lock.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/8/12
 */
public class ReentrantLockDemo4 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t我来了");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t被叫醒");
            } finally {
                lock.unlock();
            }
        }, "AA").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t叫醒你");
            } finally {
                lock.unlock();
            }
        }, "BB").start();
    }
}
