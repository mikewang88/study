package com.dougwag.action.juc.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author: MikeWang
 * @Date: 2020/6/19 2:10 PM
 * @Description:
 * https://cloud.tencent.com/developer/article/1198491
 */
public class TestObjWait {

    public static void main(String[] args)throws Exception {
        synchronizedfunicton();
    }

    private static void synchronizedfunicton() throws InterruptedException {
        final Object obj = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=0;i<10;i++){
                    sum+=i;
                }
                try {
                    synchronized (obj){
                        obj.wait();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(sum);
            }
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        Thread.sleep(1000);
        synchronized (obj){
            obj.notify();
        }
    }

    private static void locksupprotfun() throws InterruptedException {
        final Object obj = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i=0;i<10;i++){
                    sum+=i;
                }
                LockSupport.park();
                System.out.println(sum);
            }
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        //Thread.sleep(1000);
        LockSupport.unpark(A);
    }

}
