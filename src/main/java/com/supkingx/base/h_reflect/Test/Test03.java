package com.supkingx.base.h_reflect.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test03 {
    public static void main(String[] args) {
        final Class<Animal> animalClass = Animal.class;
        final Method[] declaredMethods = animalClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            // 获取注解
            final Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("注解：" + annotation);
            }

            // 获取权限修饰符
            final String s = Modifier.toString(declaredMethod.getModifiers());
            System.out.println("修饰符：" + s);

            // 返回值类型
            System.out.println("返回值类型：" + declaredMethod.getReturnType().getName());

            // 方法名
            System.out.print("方法名：" + declaredMethod.getName());

            System.out.print("(");
            // 获取形参列表
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    System.out.print(parameterTypes[i].getName() + " args_" + i);
                }
            }
            System.out.print(")");
            System.out.println();

            // 抛出异常
            final Class<?>[] exceptionTypes = declaredMethod.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    System.out.print(exceptionTypes[i].getName() + " ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }
}
