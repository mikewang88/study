package com.leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @Author: MikeWang
 * @Date: 2020/3/24 11:31 AM
 * @Description:
 * https://www.cnblogs.com/MrSaver/p/11276275.html#_label2
 * 打印零与奇偶数
 */
public class leetCode1116 {
    public static void main(String[] args) {
        // no need to { }
        start(e -> System.out.print("Release year: " + e), 2013);
    }

    public static void start(IntConsumer cons, int d) {
        cons.accept(d);
    }
}

class ZeroEvenOdd {
    private int n;
    private Semaphore s,s1,s2;


    public ZeroEvenOdd(int n) {
        this.n = n;
        s = new Semaphore(1);
        s1 = new Semaphore(0);
        s2 = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero() throws InterruptedException {
        for(int i=1;i<=n;i++)
        {
            s.acquire();
            //printNumber.accept(0);
            System.out.print(0);
            if((i&1) == 0)
                s1.release();
            else
                s2.release();
        }
    }

    public void even() throws InterruptedException {
        for(int i=2;i<=n;i+=2)
        {
            s1.acquire();
           // printNumber.accept(i);
            System.out.print(i);
            s.release();
        }
    }

    public void odd() throws InterruptedException {
        for(int i=1;i<=n;i+=2)
        {
            s2.acquire();
            //printNumber.accept(i);
            System.out.print(i);
            s.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd foo =new ZeroEvenOdd(5);
        new Thread(new zero(foo)).start();
        new Thread(new even(foo)).start();
        new Thread(new odd(foo)).start();
    }
}

class zero implements Runnable{
    private ZeroEvenOdd foo;

    public zero(ZeroEvenOdd foo){
        this.foo =foo;
    }
    @Override
    public void run() {
        try {
            foo.zero();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class even implements Runnable{
    private ZeroEvenOdd foo;

    public even(ZeroEvenOdd foo){
        this.foo =foo;
    }
    @Override
    public void run() {
        try {
            foo.even();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class odd implements Runnable{
    private ZeroEvenOdd foo;

    public odd(ZeroEvenOdd foo){
        this.foo =foo;
    }
    @Override
    public void run() {
        try {
            foo.odd();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

