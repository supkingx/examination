package com.supkingx.base.j_collection.MapDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: set不安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class NoSafeDemo {
    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,9));
                System.out.println(map);
            }).start();
        }
        // 报错：java.util.ConcurrentModificationException 线程不安全常见的异常（并发修改异常）
    }
}
