package com.supkingx.base.j_collection.ListDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @description: 集合不安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class NoSafeDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
               list.add(UUID.randomUUID().toString().substring(0,9));
                System.out.println(list);
            }).start();
        }
        // 报错：java.util.ConcurrentModificationException 线程不安全常见的异常（并发修改异常）
    }
}
