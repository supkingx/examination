package com.supkingx.base.h_reflect.Test;

import java.lang.reflect.Constructor;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test04 {
    public static void main(String[] args) {
        final Class<Animal> animalClass = Animal.class;
        // 获取声明为public的构造器
        Constructor<?>[] constructors = animalClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println();

        // 获取所有构造器
        final Constructor<?>[] declaredConstructors = animalClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
    }
}
