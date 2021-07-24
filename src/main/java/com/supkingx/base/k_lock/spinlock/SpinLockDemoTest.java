package com.supkingx.base.k_lock.spinlock;

import java.util.concurrent.TimeUnit;

/**
 * @description: 测试自定义自旋锁
 * @Author: wangchao
 * @Date: 2021/7/22
 */
public class SpinLockDemoTest {
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
            spinLockDemo.myUnLock();
        },"AA").start();

        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            spinLockDemo.myLock();
            try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        },"BB").start();
    }
}
