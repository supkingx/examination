package com.supkingx.base.b_singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @description:
 *  * 饿汉式：静态代码块饿汉式（适合复杂实例化）
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Singleton3 {

    public static final Singleton3 SINGLETON;

    private String info;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton.properties"));
            SINGLETON = new Singleton3(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Singleton3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
