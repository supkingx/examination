package com.supkingx.base.h_reflect.Test;

import java.lang.reflect.Field;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test01 {
    public static void main(String[] args) {
         Class<Animal> animalClass = Animal.class;

         // 获取public权限的字段（包含父类）
         Field[] fields = animalClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("------------------");
        // 获取所有字段（不包含父类）
        Field[] declaredFields = animalClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }
}
