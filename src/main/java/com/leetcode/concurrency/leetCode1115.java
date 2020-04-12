package com.leetcode.concurrency;

import java.util.concurrent.Semaphore;

/**
 * @Author: MikeWang
 * @Date: 2020/3/24 9:33 AM
 * @Description:
 * 交替打印FooBar（多线程）
 */
public class leetCode1115 {
    private int n;
    private Semaphore foo = new Semaphore(1);
    private Semaphore bar = new Semaphore(0);

    public leetCode1115(int n) {
        this.n = n;
    }

    public void foo() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            System.out.print("foo");
            bar.release();
        }
    }

    public void bar() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();

            // printBar.run() outputs "bar". Do not change or remove this line.
            System.out.println("bar");
            foo.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        leetCode1115 foobar = new leetCode1115(5);
        new Thread(new l1foo(foobar)).start();
        new Thread(new l2bar(foobar)).start();
    }
}

class l1foo implements Runnable{
    private leetCode1115 foo;

    public l1foo(leetCode1115 foo){
        this.foo =foo;
    }
    @Override
    public void run() {
        try {
            foo.foo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class l2bar implements Runnable{
    private leetCode1115 bar;

    public l2bar(leetCode1115 bar){
        this.bar =bar;
    }
    @Override
    public void run() {
        try {
            bar.bar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
