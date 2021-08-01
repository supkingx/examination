package com.supkingx.base.l_jvm.gc;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/26
 */
public class GCRootDemo {
//    private byte[] bytes = new byte[8 * 1024 * 1024 * 100];

//    private static GCRootDemo2 t2;
//    private static final GCRootDemo3 t3;

    public static void m1(){
//        GCRootDemo t = new GCRootDemo();
//        System.gc();
//        System.out.println("第一次GC完成");
        m1();
    }

    public static void main(String[] args) {
        m1();
    }
}
