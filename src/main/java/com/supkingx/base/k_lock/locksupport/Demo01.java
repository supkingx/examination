package com.supkingx.base.k_lock.locksupport;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/8/12
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        Thread aa = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t我来了");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t被叫醒");
        }, "AA");
        aa.start();


        Thread bb = new Thread(() -> {
            LockSupport.unpark(aa);
            System.out.println(Thread.currentThread().getName() + "\t叫醒你");
        }, "BB");
        bb.start();
    }
}
