package com.supkingx.base.h_reflect.Test;

import java.lang.annotation.Annotation;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test06 {
    public static void main(String[] args) {
        final Class<Animal> animalClass = Animal.class;
        System.out.println(animalClass.getPackage());
        for (Annotation annotation : animalClass.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
