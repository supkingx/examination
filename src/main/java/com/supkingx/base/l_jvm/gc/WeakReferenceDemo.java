package com.supkingx.base.l_jvm.gc;

import sun.jvm.hotspot.utilities.BitMap;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 弱引用 只要GC，就会被回收
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> objectSoftReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(objectSoftReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        // 内存不够用，会被回收
        System.out.println(objectSoftReference.get());

        Map<String, SoftReference<BitMap>> imageCache = new HashMap<>();
    }
}
