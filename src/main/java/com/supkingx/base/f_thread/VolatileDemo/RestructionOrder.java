package com.supkingx.base.f_thread.VolatileDemo;

/**
 * @description: 指令重排
 * @Author: wangchao
 * @Date: 2021/7/16
 */

class Demo01 {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if (flag) {
            a = a + 5;
//            System.out.println("a=" + a);
        }
    }
}

public class RestructionOrder {
    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            new Thread(() -> {
                Demo01 demo01 = new Demo01();
                demo01.method01();
                demo01.method02();
                if (demo01.a != 6) {
                    System.out.println("出现了指令重排!");
                }
            }).start();
        }
        System.out.println("结束!");
    }
}
