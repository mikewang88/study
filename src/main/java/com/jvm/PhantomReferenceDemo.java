package com.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

/**
 * @Author: MikeWang
 * @Date: 2020/6/24 11:18 AM
 * @Description: 设置虚引用的唯一目的，就是在这个对象被回收器回收的时候收到一个系统通知或者后续添加进一步的处理。
 * 如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和引用队列（RefenenceQueue）联合使用。
 * 虚引用的主要作用是跟踪对象垃圾回收的状态。仅仅是提供了一种确保对象被 finalize 以后，做某些事情的机制。
 *
 * https://www.cnblogs.com/sunshine-2015/p/9393410.html
 * System.gc不能回收堆外内存，但是会回收已经没有使用了DirectByteBuffer对象，该对象被回收的时候会将cleaner对象放入队列中，
 * 在Reference的线程中调用clean方法来回收堆外内存 。
 * cleaner.run执行的是传入参数的thunk.run方法，这里thunk是Deallocator，所以最后执行的Deallocator.run方法
 *
 * 会new DirectByteBuffer，通过Cleaner.create创建Cleaner，同时传入Deallocator作为Runnable参数，在Cleaner.clean的时候会调用该Deallocator.run来处理
Cleaner继承自PhantomReference，包含一个ReferenceQueue，在DirectByteBuffer不再使用的时候，
该对象是处于Java堆的，除了该PhantomReference引用了DirectByteBuffer外，没有其他引用的时候，jvm会把cleaner对象放入ReferenceQueue队列中。
PhantomReference继承了Reference，Reference会启动一个线程（java.lang.ref.Reference.ReferenceHandler#run）去调用队列中的cleaner.clean方法。
 *
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
//        Object o1 = new Object();
//        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<Object>();
//        PhantomReference<Object> phantomReference = new PhantomReference<Object>(o1,referenceQueue);
//
//        System.out.println(o1);
//        System.out.println(referenceQueue.poll());
//        System.out.println(phantomReference.get());
//
//        o1 = null;
//        System.gc();
//        Thread.sleep(3000);
//
//        System.out.println(o1);
//        System.out.println(referenceQueue.poll()); //引用队列中
//        System.out.println(phantomReference.get());

        final Boolean isRun = new Boolean(true);
        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        new Thread() {
            public void run() {
                while (isRun) {
                    Object obj = referenceQueue.poll();
                    if (obj != null) {
                        try {
                            Field rereferent = Reference.class.getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(obj);
                            System.out.println("gc will collect：" + result.getClass() + "@" + result.hashCode() + "\t" + (String) result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
    }
}

