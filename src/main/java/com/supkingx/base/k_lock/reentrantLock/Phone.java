package com.supkingx.base.k_lock.reentrantLock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/21
 */
public class Phone {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "---sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "------sendEmail()");
    }
}
