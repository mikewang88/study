package com.jvm;

import java.lang.ref.WeakReference;

/**
 * @Author: MikeWang
 * @Date: 2020/6/24 11:09 AM
 * @Description:
 * 弱引用
 * 对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管 JVM 的内存空间是否足够，都会回收该对象占用的内存。
 * 弱引用常被用来实现规范化映射，JDK 中的 WeakHashMap 就是一个这样的
 * 我们看下 ThreadLocal 中用到的弱引用
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> w1 = new WeakReference<Object>(o1);

        System.out.println(o1);
        System.out.println(w1.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(w1.get());
    }
}

