package com.dougwag.action.class_demo;

import com.dougwag.action.concurrentHashMap.FinalTest;

import java.util.HashMap;

/**
 * @Author: MikeWang
 * @Date: 2020/2/27 10:11 PM
 * @Description:
 */
public class ClassAndObj {
    public static void main(String[] args) {
        Object object = new Object();
        FinalTest test =new FinalTest();
        Class clazz = HashMap.class;
        System.out.println(clazz.toString());
    }

}


