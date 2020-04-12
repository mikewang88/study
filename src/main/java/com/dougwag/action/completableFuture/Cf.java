package com.dougwag.action.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: MikeWang
 * @Date: 2020/3/1 4:56 PM
 * @Description:
 */
public class Cf {
    public static void main(String[] args) throws InterruptedException {
//        CompletableFuture
//                //委托师傅做蛋糕
//                .supplyAsync(() -> {
//                    try {
//                        System.out.println("师傅准备做蛋糕");
//                        TimeUnit.SECONDS.sleep(1);
//                        System.out.println("师傅做蛋糕做好了");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return "cake";
//                })
//                //做好了告诉我一声
//                .thenAccept(cake -> {
//                    System.out.println("我吃蛋糕:" + cake);
//                });
//        System.out.println("我先去喝杯牛奶");
//        Thread.currentThread().join();


//        // 直接创建
//        CompletableFuture c0 = new CompletableFuture();
//        // 直接创建一个已经做完的蛋糕
//        CompletableFuture c1 = CompletableFuture.completedFuture("cake");
//        // 无返回值异步任务，会采用内部forkjoin线程池
//        CompletableFuture c2 = CompletableFuture.runAsync(() -> {
//        });
//        // 无返回值异步任务，采用定制的线程池
//        CompletableFuture c3 = CompletableFuture.runAsync(() -> {
//        }, newSingleThreadExecutor());
//        // 返回值异步任务，采用定制的线程池
//        CompletableFuture c4 = CompletableFuture.supplyAsync(() -> "cake", newSingleThreadExecutor());
//        // 返回值异步任务，采用内部forkjoin线程池
//        CompletableFuture c5 = CompletableFuture.supplyAsync(() -> "cake");
//        // 只要有一个完成，则完成，有一个抛异常，则携带异常
//        CompletableFuture.anyOf(c1, c2, c3, c4, c5);
//        // 当所有的 future 完成时,新的 future 同时完成
//        // 当某个方法出现了异常时,新 future 会在所有 future 完成的时候完成,并且包含一个异常.
//        CompletableFuture.allOf(c1, c2, c3, c4, c5);

//        CompletableFuture makeCake = CompletableFuture.supplyAsync(() -> {
////            System.out.println("糕点师做蛋糕");
////            return "cake";
////        });
////        makeCake.thenApplyAsync(cake -> {
////            System.out.println("蛋糕做好了，我来做牛奶");
////            return "milk";
////        })
////                .thenAcceptAsync(milk -> {
////                    System.out.println("牛奶做好了");
////                    System.out.println("我开始吃早饭");
////                })
////                .thenRunAsync(() -> {
////                    System.out.println("吃完早饭我去上班");
////                });


//        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "任务A");
//        try {
//            System.out.println(futureA.get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        futureA.thenRun(() -> System.out.println("执行任务B"));


        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        CompletableFuture<String> futureB = futureA.thenApply(s->s + " world");

        CompletableFuture<String> future3 = futureB.thenApply(String::toUpperCase);
        try {
            System.out.println(future3.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(future3.join());


    }
}
