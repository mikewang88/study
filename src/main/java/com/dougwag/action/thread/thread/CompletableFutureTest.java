package com.dougwag.action.thread.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MikeWang
 * @Date: 2020/1/13 1:51 PM
 * @Description:
 */
public class CompletableFutureTest {
    public static void main(String[] args)  throws InterruptedException {
        CompletableFuture
                //委托师傅做蛋糕
                .supplyAsync(()-> {
                    try {
                        System.out.println("师傅准备做蛋糕");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("师傅做蛋糕做好了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "cake";
                })
                //做好了告诉我一声
                .thenAccept(cake->{
                    System.out.println("我吃蛋糕:" + cake);
                });
        System.out.println("我先去喝杯牛奶");
        Thread.currentThread().join();
    }
}
