package com.supkingx.base.h_reflect.Test;

import java.io.Serializable;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/18
 */
public class Creature<T> implements Serializable {

    private static final long serialVersionUID = 6124927205618484156L;

    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }

}
