package com.supkingx.base.j_collection.MapDemo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: map安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class SafeDemo2 {
    public static void main(String[] args) {
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,9));
                System.out.println(map);
            }).start();
        }
    }
}
