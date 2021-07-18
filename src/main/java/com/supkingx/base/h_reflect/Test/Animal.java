package com.supkingx.base.h_reflect.Test;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
@MyAnnotation(value = "Animal")
public class Animal extends Creature<String> implements Comparable<String>, MyInterface {
    private String name;
    int age;
    public int id;

    public Animal() {

    }
    @MyAnnotation(value = "kkkk")
    private Animal(String name) {
        this.name = name;
    }

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @MyAnnotation(value = "show")
    private String show(String breed) {
        System.out.println("我的品种是。。。。。" + breed);
        return breed;
    }

    @MyAnnotation(value = "display")
    public String display(String interest) throws NullPointerException{
        return interest;
    }

    @Override
    public void info() {
        System.out.println("动物、。。。。");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }
}
