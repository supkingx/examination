package com.supkingx.base.h_reflect.getInstance;

import com.supkingx.base.h_reflect.Person;

/**
 * @description: 获取Class的实例方式
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 方式一： 调用运行时类的属性：class
        Class<Person> personClass = Person.class;
        System.out.println(personClass);
        // 方式二：通过运行时类的对象,调用getClass()
        Person person = new Person();
        Class aClass = person.getClass();
        System.out.println(aClass);
        // 方式三：调用Class的静态方法，forName(String classPath)
        Class clazz = Class.forName("com.supkingx.base.h_reflect.Person");
        System.out.println(clazz);
        // 方式四：使用类的加载器
        ClassLoader classLoader = Demo01.class.getClassLoader();
        Class aClass1 = classLoader.loadClass("com.supkingx.base.h_reflect.Person");
        System.out.println(aClass1);
    }
}
