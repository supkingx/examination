package com.supkingx.base.f_thread.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/26
 */
public class HoldLockThread implements Runnable {

    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    // A线程进来获得锁A，（等2s）然后B线程进来获得锁B，然后A想去获得锁B（此时B还持有锁B）
    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有lock1：" + lock1 + "，尝试获得lock2：" + lock2);
            try {
                // 等待两秒，让第二B线程启动并获取锁B
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有lock2：" + lock2 + "，尝试获得lock1：" + lock1);
            }
        }
    }
}
