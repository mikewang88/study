package com.myvip.thread;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: MikeWang
 * @Date: 2021/4/10 10:19 PM
 * @Description:
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //ThreadPoolExecutor threadPoolExecutor =  new ThreadPoolExecutor()
        Semaphore semaphore = new Semaphore(0);
        new Thread(()->{
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }
}
