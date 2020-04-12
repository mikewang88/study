package com.dougwag.action.thread.synchronize;

/**
 * @Author: MikeWang
 * @Date: 2020/3/5 2:38 PM
 * @Description:
 */
public class SynchronizedObjectLock3 implements Runnable{

    static SynchronizedObjectLock3 instance = new SynchronizedObjectLock3();

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        System.out.println("我是线程" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
    }
}

