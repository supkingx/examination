package com.supkingx.base.c_classInit;

/**
 * @description: 讲解：https://www.bilibili.com/video/BV1Eb411P7bP?p=3&spm_id_from=pageDriver
 * 先初始化父类，
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Son extends Father{
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }

    Son() {
//        super(); 子类构造器一定会调用父类构造器
        System.out.print("(7)");
    }

    // 非静态代码块
    {
        System.out.print("(8)");
    }

    @Override
    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
