package com.supkingx.base.l_jvm.gc;

import java.lang.ref.SoftReference;

/**
 * @description: 软引用（内存够用场景）
 * 不回收
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(objectSoftReference.get());

        o1= null;
        System.gc();
        System.out.println(o1);
        // 内存够用，不会被回收
        System.out.println(objectSoftReference.get());
    }
}
