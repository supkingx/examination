package com.supkingx.base.k_lock.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 共享资源类
 * @Author: wangchao
 * @Date: 2021/7/24
 */
public class MyCacheNoLock {
    /**
     * volatile关键字可以查看前面的包
     * 管缓存的，必须用volatile，保证可见性和禁止指令重排，一个线程对其进行了修改，必须让其它线程知道
     */
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 写操作：原子+独占，整个写过程必须完整的统一体，不能被分割和打断
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " 正在写入:" + "key:" + key + ",value:" + value);
        // 暂定一会模拟网络延迟
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " 写入完成:" + "key:" + key + ",value:" + value);

    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " 正在读取:" + "key:" + key);
        // 暂定一会模拟网络延迟
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取完成:" + "key:" + key + ",result:" + result);
    }

    public void clearMap() {
        map.clear();
    }
}
