package com.dougwag.action.juc.tool.condition;

/**
 * @Author: MikeWang
 * @Date: 2020/3/20 5:57 PM
 * @Description:
 */
public class SyncThread implements Runnable {
    private static int count;
    private static String lock = "lock";
    public SyncThread() {
        count = 0;
    }

    public void run() {
        synchronized (lock) {
            System.out.printf( Thread.currentThread().getName() + " enter ");
            try {
                lock.wait(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(new SyncThread(), "A");
        Thread thread2 = new Thread(new SyncThread(), "B");
        Thread thread3 = new Thread(new SyncThread(), "C");
        thread1.start();
        thread2.start();
        thread3.start();
        //A enter C enter B enter
    }
}
