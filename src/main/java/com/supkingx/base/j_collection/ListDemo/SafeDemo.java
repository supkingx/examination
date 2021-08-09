package com.supkingx.base.j_collection.ListDemo;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @description: 集合安全
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class SafeDemo {
    public static void main(String[] args) {
        List<String> list = new Vector<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
               list.add(UUID.randomUUID().toString().substring(0,9));
//                list.get()
                System.out.println(list);
            }).start();
        }
    }
}
