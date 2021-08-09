package com.supkingx.base.j_collection.Queue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 产生一个元素，消费一个元素。依次进行
 * @Author: wangchao
 * @Date: 2021/7/24
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(1);
        // SynchronousQueue 只存储单个元素,直到被消费，否则就会一直阻塞，等待被消费
        BlockingQueue<Object> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                blockingQueue.put("1");
                // 只有当SynchronousQueue里的元素被使用了，才会走到下一步，否则会一直阻塞，等待被使用
                System.out.println(Thread.currentThread().getName() + "\t put 1");

                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 2");

                blockingQueue.put("3");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
