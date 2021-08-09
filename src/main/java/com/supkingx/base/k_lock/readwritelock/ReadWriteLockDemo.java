package com.supkingx.base.k_lock.readwritelock;

/**
 * @description: 读写锁
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是如果有一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读或写。
 * <p>
 * 小总结：
 * 读-读共存
 * 读-写不能共存
 * 写-写不能共存
 * <p>
 * 写操作：原子+独占，整个写过程必须完整的统一体，不能被分割和打断
 * @Author: wangchao
 * @Date: 2021/7/24
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
//        MyCacheNoLock myCache = new MyCacheNoLock();
        for (int i = 0; i < 5; i++) {
            final int i1 = i;
            new Thread(() -> {
                myCache.put(i1 + "", i1 + "");
            }, String.valueOf(i)).start();

            new Thread(() -> {
                myCache.get(i1 + "");
            }, String.valueOf(i)).start();

            new Thread(() -> {
                myCache.get(i1 + "");
            }, String.valueOf(i)).start();
        }
    }
}
