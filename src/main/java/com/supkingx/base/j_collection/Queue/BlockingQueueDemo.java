package com.supkingx.base.j_collection.Queue;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: 1、队列
 * 2、阻塞队列
 * 2.1 阻塞队列有没有好的一面
 * @Author: wangchao
 * @Date: 2021/7/24
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(1);
        BlockingQueue<Object> blockingQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 1");

                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 2");

                blockingQueue.put("3");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
