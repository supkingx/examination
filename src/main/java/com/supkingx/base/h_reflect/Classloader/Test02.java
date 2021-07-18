package com.supkingx.base.h_reflect.Classloader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Test02 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        // 读取配置方式一
//        FileInputStream fileInputStream = new FileInputStream("/Users/superking/Documents/project/examination/src/main/resources/jdbc.properties");
       // 方式二：
        InputStream resourceAsStream = Test02.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(resourceAsStream);
        String user = (String) properties.get("user");
        String password = (String) properties.get("password");
        System.out.println("user:" + user + "," + "password:" + password);

    }
}
