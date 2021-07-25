package com.supkingx.base.k_lock.exam;

/**
 * @description:多线程之间按照顺序调用，实现A->B->C 三个线程启动，要求如下：
 * <p>
 * AA打印5次，BB打印10次，CC打印5次；
 * 紧接着，
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。。依次来十轮
 * @Author: wangchao
 * @Date: 2021/7/25
 */
public class Test01 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        // A线程循环了10次，但是第一个循环执行完后便进入的wait阶段，并唤起B,后面9次循环都没开始
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        // B第一次循环直接进入wait，等被A唤醒后，完成第一次循环，并唤起C,后面9次循环都没开始
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();

        // C第一次循环直接进入wait，等被B唤醒后，完成第一次循环，并唤起A,后面9次循环都没开始
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}
