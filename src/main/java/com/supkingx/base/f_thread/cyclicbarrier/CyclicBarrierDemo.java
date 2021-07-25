package com.supkingx.base.f_thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier每次加1
 * @Author: wangchao
 * @Date: 2021/7/24
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        // 当计数器达到7后，输出文字
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("召唤神龙"));
        for (int i = 1; i <= 7; i++) {
            final int i1 = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到第 " + i1 + " 颗龙珠");
                try {
                    // 加一
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
