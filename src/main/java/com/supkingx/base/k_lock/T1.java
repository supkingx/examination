package com.supkingx.base.k_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/21
 */
public class T1 {
    volatile int n = 0;
    public void add(){
        n++;
    }

    public static void main(String[] args) {
        // 可重入锁，点进去看源码
        Lock lock = new ReentrantLock(true);
        Lock lock2 = new ReentrantLock();
    }
}
