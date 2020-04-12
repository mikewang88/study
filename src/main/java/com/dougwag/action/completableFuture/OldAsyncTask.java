package com.dougwag.action.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: MikeWang
 * @Date: 2020/3/2 10:02 AM
 * @Description:
 */
public class OldAsyncTask {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "async thread";
            }
        });
        Thread.sleep(1000);
        System.out.println("main thread");
        System.out.println(stringFuture.get());
        System.out.println("main thread over");

    }
}
