package com.supkingx.base.l_jvm.gc.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: 模拟报错 OutOfMemoryError: Metaspace
 * -XX:MetaspaceSize=5m -XX:MaxMetaspaceSize=20m
 * @Author: wangchao
 * @Date: 2021/7/31
 */
public class MetaspaceOOMDemo {
    static class OOMTest {
        private int a;

        public void setA(int a) {
            this.a = a;
        }
    }

    public static void main(String[] args) {
        int i = 0;

        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, objects);
                    }
                });
                Object o = enhancer.create();
//                OOMTest oomTest = new OOMTest();
//                oomTest.setA(i);
//                System.out.println("创建类:" + oomTest);
            }
        } catch (Throwable e) {
            System.out.println("多少次后发生了异常：" + i);
            e.printStackTrace();
        }
    }
}
