package com.supkingx.base.k_lock.exam;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 共享资源类
 * @Author: wangchao
 * @Date: 2021/7/25
 */
public class ShareResource {
    private int number = 1; // a:1 b:2 c:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    // a干完后等待，唤起b
    public void print5() {
        lock.lock();
        try {
            // 1、判断
            while (number != 1) {
                c1.await();
            }
            // 2、干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3、通知
            number=2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // b干完后等待，唤起c
    public void print10() {
        lock.lock();
        try {
            // 1、判断
            while (number != 2) {
                c2.await();
            }
            // 2、干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3、通知
            number=3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // c干完后等待，唤起a
    public void print15() {
        lock.lock();
        try {
            // 1、判断
            while (number != 3) {
                c3.await();
            }
            // 2、干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            // 3、通知
            number=1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
