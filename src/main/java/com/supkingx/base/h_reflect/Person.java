package com.supkingx.base.h_reflect;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Person {
    private int age;
    private String name;

    public Person() {
        System.out.println("无参构造器");
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private Person(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("秀一下");
    }

    public String display(String str) {
        System.out.println("玩一下");
        return str;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
