package com.dougwag.action.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: MikeWang
 * @Date: 2020/3/9 1:46 PM
 * @Description:
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);

        MyRtThread t1 = new MyRtThread("t1", lock);
        MyRtThread t2 = new MyRtThread("t2", lock);
        MyRtThread t3 = new MyRtThread("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }
}


class MyRtThread extends Thread {
    private Lock lock;
    public MyRtThread(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}
