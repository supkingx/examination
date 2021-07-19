package com.supkingx.base.j_collection.ListDemo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: 集合安全
 *
 * CopyOnWrite容器即写时复制容器，往一个容器中添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器进行copy，复制出一个新容器 Object[] newElements，新容器容量比原容器大1，将新元素放入新容器的最后，最后将原容器的引用指向新容器setArray(newElements);
 * 这样做的好处是可以对CopyOnWrite容器进行并发读，而不需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器也是一种读写分离的思想，读和写时不同的容器。
 *
 * public boolean add(E e) {
 *     final ReentrantLock lock = this.lock;
 *     lock.lock();
 *     try {
 *         Object[] elements = getArray();
 *         int len = elements.length;
 *         Object[] newElements = Arrays.copyOf(elements, len + 1);
 *         newElements[len] = e;
 *         setArray(newElements);
 *         return true;
 *     } finally {
 *         lock.unlock();
 *     }
 * }
 * @Author: wangchao
 * @Date: 2021/7/19
 */
public class SafeDemo3 {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
               list.add(UUID.randomUUID().toString().substring(0,9));
                System.out.println(list);
            }).start();
        }
    }
}
