package com.dougwag.action.base.parameter_pass;

import java.util.ArrayDeque;

/**
 * @Author: MikeWang
 * @Date: 2020/3/6 4:40 PM
 * @Description:
 *
 *
Java 的参数是以值传递的形式传入方法中，而不是引用传递。
以下代码中 Dog dog 的 dog 是一个指针，存储的是对象的地址。在将一个参数传入一个方法时，
本质上是将对象的 地址 以 值 的方式传递到形参中。
因此在方法中改变指针引用的对象，那么这两个指针此时指向的是完全不同的对象，一方改变其所指向对象的内容对另一方没有影响。


但是如果在方法中 改变 对象的字段值 会改变 原对象该字段值，因为改变的是同一个地址指向的内容。
 */
public class PassByValueExample {
    public static void main(String[] args) {
        Dog dog = new Dog("A");
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        func(dog);
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        System.out.println(dog.getName());// A

        func2(dog);
        System.out.println(dog.getName());// B

    }

    private static void func(Dog dog) {
        System.out.println(dog.getObjectAddress()); // Dog@4554617c
        dog = new Dog("B");
        System.out.println(dog.getObjectAddress()); // Dog@74a14482
        System.out.println(dog.getName());          // B
    }

    private static void func2(Dog dog) {
        dog.setName("B");
    }
}

