package com.supkingx.base.l_jvm.gc.oom;

import java.util.ArrayList;

/**
 * @description: 模拟 java.lang.OutOfMemoryError: GC overhead limit exceeded
 * -Xms10m -Xmx20m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * MaxDirectMemorySize：直接内存大小，因为本机内存太大，所以设置的小一点
 *
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class GCOverheadDemo {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        ArrayList<Object> list = new ArrayList<>();
        try {
            while (true){
                // TODO 记得关注 https://www.runoob.com/java/java-string-intern.html
                list.add(String.valueOf(++i).intern());
                if(i==100){
                    Thread.sleep(10000000);
                }
                Thread.sleep(1);
            }
        }catch (Throwable e){
            System.out.println("***************i:"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
