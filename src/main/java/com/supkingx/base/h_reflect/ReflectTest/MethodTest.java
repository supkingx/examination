package com.supkingx.base.h_reflect.ReflectTest;

import com.supkingx.base.h_reflect.Person;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class MethodTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();

        final Method show = personClass.getDeclaredMethod("show");
        show.invoke(person);

        // 第二个参数是返回类型
        final Method display = personClass.getDeclaredMethod("display", String.class);
        System.out.println(display.invoke(person, "哈哈哈"));

    }
}
