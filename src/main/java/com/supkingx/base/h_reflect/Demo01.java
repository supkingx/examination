package com.supkingx.base.h_reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Demo01 {
    public static void main(String[] args) throws Exception {

        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getConstructor(int.class,String.class);
        // 创建了这个对象
        Person king = constructor.newInstance( 12,"king");

        System.out.println(king);
        // 获取class中的这个字段
        Field age = personClass.getDeclaredField("age");
        // 无视private
        age.setAccessible(true);
        // 给king对象中的age字段设置参数
        age.set(king,100);
        System.out.println(king);
        age.setAccessible(false);

        // 获取class中的show方法
        Method show = personClass.getDeclaredMethod("show");
        // 执行king对象中的show方法
        show.invoke(king);
    }
}
