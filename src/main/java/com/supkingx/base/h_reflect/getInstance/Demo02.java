package com.supkingx.base.h_reflect.getInstance;

import com.supkingx.base.h_reflect.Person;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Demo02 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<Person> personClass = Person.class;
        // 调用此方法创建运行时类的对象（内部调用运行实时类的空参构造器）
        // 想要用此方法创建，必须提供public权限的空参构造器

        // 在javabean中要求提供一个public的空参构造器，原因：
        // 便于反射创建运行时类的对象
        // 便于子类继承此运行时类，默认调用super()时，保证父类有次构造器
        Person person = personClass.newInstance();
        System.out.println(person);
    }
}
