package com.supkingx.base.h_reflect.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test05 {
    public static void main(String[] args) {
        final Class<Animal> animalClass = Animal.class;
        // 获取运行时类的父类
        System.out.println(animalClass.getSuperclass());
        System.out.println("--------------------------");

        // 获取运行时类的父类(带泛型)
        Type genericSuperclass = animalClass.getGenericSuperclass();
        System.out.println(genericSuperclass);
        System.out.println("--------------------------");

        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        // 获取实际的运行参数(泛型参数)
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument.getTypeName());
        }
        System.out.println("--------------------------");

        final Class<?>[] interfaces = animalClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
        System.out.println("--------------------------");

        final Class<?>[] supInterfaces = animalClass.getSuperclass().getInterfaces();
        for (Class<?> anInterface : supInterfaces) {
            System.out.println(anInterface);
        }
        System.out.println("--------------------------");


    }
}
