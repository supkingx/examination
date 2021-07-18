package com.supkingx.base.h_reflect.ReflectTest;

import com.supkingx.base.h_reflect.Person;

import java.lang.reflect.Field;

/**
 * @description: 调用运行时类中指定的结构：属性、方法、构造器
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class FieldTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();
        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person,"king");
        System.out.println(person);

        String name = (String)nameField.get(person);
        System.out.println(name);
    }
}
