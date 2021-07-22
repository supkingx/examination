package com.supkingx.base.k_lock.reentrantLock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/21
 */
public class ReentrantLockDemo2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        Thread thread1 = new Thread(phone);
        Thread thread2 = new Thread(phone);

        thread1.start();
        thread2.start();
    }
}
