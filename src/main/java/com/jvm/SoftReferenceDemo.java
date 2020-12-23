package com.jvm;

import java.lang.ref.SoftReference;

/**
 * @Author: MikeWang
 * @Date: 2020/6/24 10:03 AM
 * @Description:
 * https://juejin.im/post/5ec73c99f265da770c0ee3b1
 * //VM options: -Xms5m -Xmx5m -XX:+PrintGC
 * 软引用
 * 软引用通常用在对内存敏感的程序中，比如高速缓存就有用到软引用，内存够用的时候就保留，不够用就回收。我们看下 Mybatis 缓存类 SoftCache 用到的软引用
 * 反射使用软引用relectionData缓存class信息，避免每次重新从jvm获取带来的开销
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        softRefMemoryEnough();
        System.out.println("------内存不够用的情况------");
        softRefMemoryNotEnough();
    }

    private static void softRefMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> s1 = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(s1.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(s1.get());
    }

    /**
     * JVM配置`-Xms5m -Xmx5m` ，然后故意new一个一个大对象，使内存不足产生 OOM，看软引用回收情况
     */
    private static void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> s1 = new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(s1.get());

        o1 = null;

        byte[] bytes = new byte[10 * 1024 * 1024];

        System.out.println(o1);
        System.out.println(s1.get());
    }
}

