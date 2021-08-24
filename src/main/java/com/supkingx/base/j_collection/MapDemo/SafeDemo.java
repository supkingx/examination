package com.supkingx.base.j_collection.MapDemo;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: map安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class SafeDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,9));
                System.out.println(map);
            }).start();
        }
    }
}
