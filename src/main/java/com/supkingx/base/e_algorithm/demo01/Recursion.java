package com.supkingx.base.e_algorithm.demo01;

/**
 * @description:  递归
 *
 * 有n步台阶，一次只能上1步或2步，共有几种走法？
 * 设f(n)=x，表示有n个台阶时，有x中走法
 * <p>
 * - n=1，一步   f(1)=1
 * - n=2，一步一步 或者 直接两步    f(2)=2
 * - n=3，
 * - 先到达f(1)，然后f(1)直接跨2步。
 * - 先到达f(2)，然后f(2)跨1步。
 * - 即 f(3) = f(1)+f(2)，表示，到f(1)的走法+到f(2)的走法
 * - n=4，
 * - 先到达f(2)，然后从f(2)直接跨2步
 * - 先到达f(3)，然后f(3)跨1步
 * - 即 f(4) = f(2)+f(3)，表示到f(2)的走法+到f(3)的走法
 * - ........
 * - n=x
 * - 先到达f(x-2)，然后从f(x-2)直接跨2步
 * - 先到达f(x-1)，然后f(x-1)直接跨1步
 * - 即f(x)=f(x-2)+f(x-1)，表示到f(x-2)的走法+到f(x-1)的走法
 * @Author: wangchao
 * @Date: 2021/7/12
 */
public class Recursion {
    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        System.out.println("共有走法 " + fun(40) + " 种");
        final long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - start) + "ms");
    }

    private static int fun(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return fun(n - 2) + fun(n - 1);
    }
}
