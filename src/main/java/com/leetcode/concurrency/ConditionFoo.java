package com.leetcode.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 5:57 PM
 * @Description:
 */
public class ConditionFoo {
    private int signal;// 还是需要通过信号量控制abc的输出顺序

    Lock lock = new ReentrantLock(); // 只有一个锁
    Condition l1 = lock.newCondition();// 多个condition
    Condition l2 = lock.newCondition();
    Condition l3 = lock.newCondition();

    public void first() {// 去掉了synchronized，取而代之的是reentrantlock的lock和unlock。
        lock.lock();
        while (signal != 0) {
            try {
                l1.await();// 释放cpu的执行资格和执行权，同时释放锁
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("one");
        signal++;
        l2.signal();// 这里只唤醒了b
        lock.unlock();
    }

    public void second() {
        lock.lock();
        while (signal != 1) {
            try {
                l2.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("two");
        signal++;
        l3.signal();
        lock.unlock();
    }

    public void third() {
        lock.lock();
        while (signal != 2) {
            try {
                l3.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("third");
        System.out.println("================");
        signal = 0;
        l1.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        ConditionFoo e = new ConditionFoo();
        T1 AA = new T1(e);
        T2 BB = new T2(e);
        T3 CC = new T3(e);
        new Thread(AA).start();
        new Thread(CC).start();
        new Thread(BB).start();
    }



}

class T1 implements Runnable {
    private ConditionFoo e;

    public T1(ConditionFoo e) {
        this.e = e;

    }

    @Override
    public void run() {
//        while (true) {
            e.first();
//        }
    }
}

class T2 implements Runnable {
    private ConditionFoo e;

    public T2(ConditionFoo e) {
        this.e = e;

    }

    @Override
    public void run() {
//        while (true) {
            e.second();
//        }
    }
}
class T3 implements Runnable {
    private ConditionFoo e;

    public T3(ConditionFoo e) {
        this.e = e;

    }

    @Override
    public void run() {
//        while (true) {
            e.third();
//        }
    }
}
