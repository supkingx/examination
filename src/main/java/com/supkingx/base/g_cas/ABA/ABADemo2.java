package com.supkingx.base.g_cas.ABA;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description: 演示ABA问题解决
 * @Author: wangchao
 * @Date: 2021/7/17
 */
public class ABADemo2 {

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "-第一次版本号：" + stamp);
            // 暂停一秒A线程，等B线程拿到这个版本号
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "-第二次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "-第三次版本号：" + atomicStampedReference.getStamp());
        }, "A").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "-第一次版本号：" + stamp);
            // 暂停三秒B线程，保证上面的A线程完成一次ABA操作
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100, 2021, stamp, stamp + 1);
            // 修改代码如下，期望的时间戳是stamp+2,即3（与上面的最新时间戳保持一致）时，即可需改成功
//            boolean result = atomicStampedReference.compareAndSet(100, 2021, stamp+2, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "修改成功与否:" + result + ",第二次版本号:" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "-当前实际最新值:"+atomicStampedReference.getReference());
        }, "B").start();
    }
}
