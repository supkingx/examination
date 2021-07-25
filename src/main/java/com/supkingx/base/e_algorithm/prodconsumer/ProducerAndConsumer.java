package com.supkingx.base.e_algorithm.prodconsumer;

/**
 * @description: 生产者和消费者
 * 一个初始值为零的变量，两个线程对其交替操作，一个+1，一个-1，来5轮
 * @Author: wangchao
 * @Date: 2021/7/25
 */
public class ProducerAndConsumer {
    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        },"BB").start();
    }
}
