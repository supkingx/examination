package com.supkingx.base.f_thread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: 每次减一
 * @Author: wangchao
 * @Date: 2021/7/24
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 上限3个，到了3后会阻塞，等待信号量-1
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 加1
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢车位");
                    TimeUnit.SECONDS.sleep(6);
                    System.out.println(Thread.currentThread().getName()+"\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 减1
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
