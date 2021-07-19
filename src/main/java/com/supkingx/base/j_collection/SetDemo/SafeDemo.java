package com.supkingx.base.j_collection.SetDemo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @description: set安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class SafeDemo {
    public static void main(String[] args) {
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,9));
                System.out.println(set);
            }).start();
        }
    }
}
