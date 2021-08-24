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
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        // 会被回收
        System.out.println(weakReference.get());

        Map<String, SoftReference<BitMap>> imageCache = new HashMap<>();
    }
}
