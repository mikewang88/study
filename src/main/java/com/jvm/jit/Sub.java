package com.jvm.jit;

/**
 * @Author: MikeWang
 * @Date: 2020/5/28 5:35 PM
 * @Description:
 */
public class Sub extends Parent{
    public static String subStr= "sub static string";
    static{
        System.out.println("sub static fields");
        System.out.println(subStr);
    }

    public Sub(){
        System.out.println("sub instance initialization");
    }

    public static void main(String[] args){
        System.out.println("sub main");
        new Sub();
        new Sub();
    }
}
