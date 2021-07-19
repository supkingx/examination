package com.supkingx.base.j_collection.SetDemo;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description: set安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class SafeDemo2 {
    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,9));
                System.out.println(set);
            }).start();
        }
    }
}
