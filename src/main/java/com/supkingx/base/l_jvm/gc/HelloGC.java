package com.supkingx.base.l_jvm.gc;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/27
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TOTAL_MEMORY(-Xms) = "+totalMemory + "(字节)、"+(totalMemory/(double)1024/1024)+"MB");
        System.out.println("MAX_MEMORY(-Xmx) = "+maxMemory + "(字节)、"+(maxMemory/(double)1024/1024)+"MB");
        System.out.println("-f**************hello gc");
        byte[] bytes = new byte[50 * 1024 * 1024];
//        Thread.sleep(Integer.MAX_VALUE);
    }
}
