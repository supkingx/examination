package com.supkingx.base.l_jvm.gc;

import java.lang.ref.SoftReference;

/**
 * @description: 强引用示例，只要引用存在 永远不会被回收
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(objectSoftReference.get());

        o1 = null;

        System.out.println(o1);
        // 内存不够用，会被回收
        System.out.println(objectSoftReference.get());
    }
}
