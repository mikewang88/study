package com.dougwag.action.juc.myself;

import java.util.concurrent.locks.Lock;

/**
 * @Author: MikeWang
 * @Date: 2020/3/9 2:39 PM
 * @Description:
 */
public class SelfLockDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new MyReentrantLock();

        MyRtThread2 t1 = new MyRtThread2("t1", lock);
        MyRtThread2 t2 = new MyRtThread2("t2", lock);
        MyRtThread2 t3 = new MyRtThread2("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }
}


class MyRtThread2 extends Thread {
    private Lock lock;
    public MyRtThread2(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}

