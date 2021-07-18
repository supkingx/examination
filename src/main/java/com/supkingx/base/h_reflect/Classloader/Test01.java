package com.supkingx.base.h_reflect.Classloader;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取系统类加载器
        ClassLoader classLoader = Test01.class.getClassLoader();
        Class aClass1 = classLoader.loadClass("com.supkingx.base.h_reflect.Person");
        System.out.println(aClass1);
        System.out.println(classLoader);
        // 通过系统类加载器的getParent()，获取扩展类加载器
        System.out.println(classLoader.getParent());
        // 通过扩展类加载器的getParent()，获取引导类加载器
        // 引导类加载器主要负责java的核心类库，无法加载自定义类
        System.out.println(classLoader.getParent().getParent());
    }
}
