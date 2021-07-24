package com.supkingx.base.j_collection.SetDemo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @description: set不安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class NoSafeDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,9));
                System.out.println(set);
            }).start();
        }
        // 报错：java.util.ConcurrentModificationException 线程不安全常见的异常（并发修改异常）
    }
}
