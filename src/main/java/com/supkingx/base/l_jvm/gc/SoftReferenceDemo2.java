package com.supkingx.base.l_jvm.gc;

import java.lang.ref.SoftReference;

/**
 * @description: 软引用（内存 不够用场景）
 * 配置小内存 -Xms10m -Xmx10m
 * 大对象new byte[30 * 1024 * 1024] 使其OOM
 * 回收
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class SoftReferenceDemo2 {
    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(objectSoftReference.get());

        o1= null;
        try{
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            // 内存不够用，会被回收
            System.out.println(objectSoftReference.get());
        }
    }
}
