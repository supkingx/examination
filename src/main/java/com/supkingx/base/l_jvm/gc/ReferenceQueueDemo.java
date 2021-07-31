package com.supkingx.base.l_jvm.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @description: 弱引用
 * GC将要开始的时候，对象会被放入引用队列
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o, referenceQueue);

        System.out.println(o);
        System.out.println(weakReference.get());
        // 弱引用
        System.out.println("gc之前的弱引用队列" + referenceQueue.poll());
        o = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("gc之后的对象" + o);
        System.out.println("gc之后的虚引用" + weakReference.get());
        System.out.println("gc之后的弱引用队列" + referenceQueue.poll());
    }
}
