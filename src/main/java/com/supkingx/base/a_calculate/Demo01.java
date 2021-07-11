package com.supkingx.base.a_calculate;

/**
 * @description:
 * 讲解视频参考：https://www.bilibili.com/video/BV1Eb411P7bP?t=46
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Demo01 {
    public static void main(String[] args) {
        int i = 1;
        i = i++;    // i = 1
        int j = i++;   // j=1
        int k = i + ++i * i++; // k=2+3*3 = 11  i=4
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
