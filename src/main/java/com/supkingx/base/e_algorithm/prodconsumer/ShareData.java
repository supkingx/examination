package com.supkingx.base.e_algorithm.prodconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 资源类
 * 注意：多线程的判断不能用if，要用while，因为有虚假唤醒
 * @Author: wangchao
 * @Date: 2021/7/25
 */
public class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            // 1、判断
            while (number != 0) {
                condition.await();
            }
            // 2、干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3、通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            // 1、判断
            while (number == 0) {
                condition.await();
            }
            // 2、干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // 3、通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
