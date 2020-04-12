package com.dougwag.action.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: MikeWang
 * @Date: 2020/3/6 3:21 PM
 * @Description:
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        //买锅
      Thread t1 = new Thread(new BuyGuo(Thread.currentThread()));
      t1.start();
        LockSupport.park();
        //买菜
        Thread t2 = new Thread(new BuyCai(Thread.currentThread()));
        t2.start();
        LockSupport.park();
        System.out.println("锅买回来了...");

        System.out.println("菜买回来 了...");
        System.out.println("开始做饭");
    }
}
class BuyGuo implements Runnable{
    private Object threadObj;
    public BuyGuo(Object threadObj) {
        this.threadObj = threadObj;
    }

    @Override
    public void run() {
        System.out.println("去买锅...");
        LockSupport.unpark((Thread)threadObj);

    }
}
class BuyCai implements Runnable{
    private Object threadObj;
    public BuyCai(Object threadObj) {
        this.threadObj = threadObj;
    }

    @Override
    public void run() {
        System.out.println("买菜去...");
        LockSupport.unpark((Thread)threadObj);
    }
}
