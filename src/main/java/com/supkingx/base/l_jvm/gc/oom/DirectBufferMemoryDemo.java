package com.supkingx.base.l_jvm.gc.oom;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * @description: 模拟 OutOfMemoryError: Direct buffer memory
 * -Xms10m -Xmx20m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:"+(VM.maxDirectMemory()/(double)1024/1024)+"MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
