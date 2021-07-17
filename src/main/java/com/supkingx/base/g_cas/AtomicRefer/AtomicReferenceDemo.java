package com.supkingx.base.g_cas.AtomicRefer;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: AtomicReference 的使用，原子引用。原子包装
 * @Author: wangchao
 * @Date: 2021/7/17
 */

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User king = new User("king",29);
        User supking = new User("supking",39);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(king);

        System.out.println(userAtomicReference.compareAndSet(king,supking)+"--"+userAtomicReference.get());
        System.out.println(userAtomicReference.compareAndSet(king,supking)+"--"+userAtomicReference.get());
    }
}
