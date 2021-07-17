package com.supkingx.base.g_cas.ABA;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 演示ABA问题产生
 *
 * @Author: wangchao
 * @Date: 2021/7/17
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(atomicReference.compareAndSet(100,101)+"--"+atomicReference.get());
            System.out.println(atomicReference.compareAndSet(101,100)+"--"+atomicReference.get());
        },"A").start();

        // B 线程并不知道A的过程发生了什么，只知道A的值是100，并不知道A中途变成过101
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100,2021)+"--"+atomicReference.get());
        },"B").start();
    }
}
