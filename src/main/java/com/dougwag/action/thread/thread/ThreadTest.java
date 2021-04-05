package com.dougwag.action.thread.thread;

/**
 * @Author: MikeWang
 * @Date: 2021/4/4 11:31 PM
 * @Description:
 */
public class ThreadTest {
    static class ThreadA extends Thread {
        @Override
        public void run() {
            System.out.println("Hello World");
        }
    }

    public static void main(String[] args) {
        ThreadA t = new ThreadA();
        t.start();
    }
}
