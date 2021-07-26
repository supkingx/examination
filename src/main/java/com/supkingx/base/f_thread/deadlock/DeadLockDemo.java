package com.supkingx.base.f_thread.deadlock;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/26
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();
    }
}
