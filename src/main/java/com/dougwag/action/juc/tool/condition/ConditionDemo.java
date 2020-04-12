package com.dougwag.action.juc.tool.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: MikeWang
 * @Date: 2020/3/20 5:40 PM
 * @Description:
 */
public class ConditionDemo {
    private int signal;// 还是需要通过信号量控制abc的输出顺序

    Lock lock = new ReentrantLock(); // 只有一个锁
    Condition AA = lock.newCondition();// 多个condition
    Condition BB = lock.newCondition();
    Condition CC = lock.newCondition();

    public void AA() {// 去掉了synchronized，取而代之的是reentrantlock的lock和unlock。
        lock.lock();
        while (signal != 0) {
            try {
                AA.await();// 释放cpu的执行资格和执行权，同时释放锁
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("AA");
        signal++;
        BB.signal();// 这里只唤醒了b
        lock.unlock();
    }

    public void BB() {
        lock.lock();
        while (signal != 1) {
            try {
                BB.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("BB");
        signal++;
        CC.signal();
        lock.unlock();
    }

    public void CC() {
        lock.lock();
        while (signal != 2) {
            try {
                CC.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("CC");
        System.out.println("================");
        signal = 0;
        AA.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        ConditionDemo e = new ConditionDemo();
        AA AA = new AA(e);
        BB BB = new BB(e);
        CC CC = new CC(e);
        new Thread(AA).start();
        new Thread(CC).start();
        new Thread(BB).start();


    }
}

class AA implements Runnable {
    private ConditionDemo e;

    public AA(ConditionDemo e) {
        this.e = e;

    }

    @Override
    public void run() {
        while (true) {
            e.AA();
        }
    }
}

class BB implements Runnable {
    private ConditionDemo e;

    public BB(ConditionDemo e) {
        this.e = e;

    }

    @Override
    public void run() {
        while (true) {
            e.BB();
        }
    }
}

class CC implements Runnable {
    private ConditionDemo e;

    public CC(ConditionDemo e) {
        this.e = e;

    }

    @Override
    public void run() {
        while (true) {
            e.CC();
        }
    }

}

