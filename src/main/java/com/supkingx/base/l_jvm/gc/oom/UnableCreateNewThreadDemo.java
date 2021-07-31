package com.supkingx.base.l_jvm.gc.oom;

/**
 * @description: 模拟报错  unable to create new native thread
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "start");
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }
    }
}
