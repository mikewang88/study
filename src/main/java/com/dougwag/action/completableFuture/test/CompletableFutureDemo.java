package com.dougwag.action.completableFuture.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: MikeWang
 * @Date: 2020/3/3 10:40 PM
 * @Description:
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        AtomicReference<Boolean> otherWork = new AtomicReference<>(true);
        CompletableFuture<Void>  cf =  CompletableFuture
                .supplyAsync(() -> {
                    try {
                        System.out.println("洗水壶，插电源，开始烧水");
                        TimeUnit.SECONDS.sleep(6);
                        System.out.println("水烧开了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "水好了";
                })
                //水好了告诉我一声
                .thenAccept(cake -> {
                    otherWork.set(false);
                    System.out.println(cake+" 开始泡茶--》 喝茶");
                });
        try {
            System.out.println("我先去 洗茶杯");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("洗完了，放茶叶");
            while (otherWork.get()){
                System.out.println("看书。。。");
                TimeUnit.SECONDS.sleep(1);
            }
            //cf.join();///????
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
