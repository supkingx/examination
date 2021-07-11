package com.supkingx.base.c_classInit;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Father {
    /**
     * 这里的test()
     * 因为非静态方法前面其实有一个默认的对象this,this在构造器（或<init>）它表示的是正在创建的对象，因为此时是子类的test()运行，
     * 这里父类的i=test() 执行的是子类重写的test()方法
     */
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }

    Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}
