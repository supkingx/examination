package com.supkingx.base.a_calculate;

/**
 * @description: 成员变量与局部变量
 * @Author: wangchao
 * @Date: 2021/7/12
 */
public class Demo02 {
    static int s; // 成员变量--类变量
    int i; // 成员变量--实例变量
    int j; // 成员变量--实例变量

    {
        int i = 1; // 非静态代码块中的局部变量i
        i++;
        j++;
        s++;
    }

    public void test(int j) { // 形参，局部变量,j
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) { // 形参，局部变量 args
        Demo02 obj1 = new Demo02();  // 局部变量,obj1    i=0，j=1，s=1
        Demo02 obj2 = new Demo02();  // 局部变量,obj2    i=0，j=1，s=2

        obj1.test(10);  // i=1,j=1,s=3
        obj1.test(20);  // i=2,j=1,s=4
        obj2.test(30);  // i=1,j=1,s=5

        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }
}
