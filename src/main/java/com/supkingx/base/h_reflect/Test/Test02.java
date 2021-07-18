package com.supkingx.base.h_reflect.Test;

import com.supkingx.base.h_reflect.Person;

import java.lang.reflect.Method;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test02 {
    public static void main(String[] args) {
        Class animalClass = Animal.class;
        // 获取运行时类及其父类所有声明为public 的方法
        Method[] methods = animalClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("---------------");

        // 获取运行时类所声明的所有的方法(不包含父类)
        final Method[] declaredMethods = animalClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }
}
