package com.jvm.jit;

/**
 * @Author: MikeWang
 * @Date: 2020/5/28 5:35 PM
 * @Description:
 */
public class Parent {
    public static String parentStr= "parent static string";
    static{
        System.out.println("parent static fields");
        System.out.println(parentStr);
    }
    public Parent(){
        System.out.println("parent instance initialization");
    }
}
