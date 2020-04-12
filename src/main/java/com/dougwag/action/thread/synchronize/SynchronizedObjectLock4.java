package com.dougwag.action.thread.synchronize;

/**
 * @Author: MikeWang
 * @Date: 2020/3/5 2:42 PM
 * @Description:
 */
public class SynchronizedObjectLock4 implements Runnable{
    static SynchronizedObjectLock4 instance1 = new SynchronizedObjectLock4();
    static SynchronizedObjectLock4 instance2 = new SynchronizedObjectLock4();

    @Override
    public void run() {
        method();
    }

    // synchronized用在普通方法上，默认的锁就是this，当前实例
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
        // t1和t2对应的this是两个不同的实例，所以代码不会串行
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
    }
}
