package com.supkingx.base.b_singleton;

/**
 * @description: 懒汉式
 * 在内部类被加载和初始化时，才创建SINGLETON实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独加载和初始化的。
 * 因为是在内部类加载和初始化时创建的，所以是线程安全的。
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Singleton6 {
    private Singleton6() {
    }

    private static class Inner {
        private static final Singleton6 SINGLETON = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return Inner.SINGLETON;
    }
}
