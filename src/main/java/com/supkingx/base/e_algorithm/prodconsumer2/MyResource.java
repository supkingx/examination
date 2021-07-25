package com.supkingx.base.e_algorithm.prodconsumer2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/25
 */
public class MyResource {
    // 利用volatile修饰，提高可见性
    private volatile boolean FLAG = true; // 默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            // 向队列添加数据，队列满了则等待2秒
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            // 降低生产频率，给消费以时间
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        System.out.println(Thread.currentThread().getName() + "\t大老板叫停了，表示FLAG=false,生产动作结束");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG) {
            // 2s取不到，就不取了
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2s没取到");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}
