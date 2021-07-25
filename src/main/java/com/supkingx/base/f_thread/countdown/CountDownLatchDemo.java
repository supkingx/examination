package com.supkingx.base.f_thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 必须等上自习的同学全部离开后，班长才能关门
 * @Author: wangchao
 * @Date: 2021/7/24
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "国灭亡");
                countDownLatch.countDown();
            }, CountryEnum.getName(i)).start();
        }
        // 等countDownLatch到0，才执行
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "-----秦一统天下");
    }
}
