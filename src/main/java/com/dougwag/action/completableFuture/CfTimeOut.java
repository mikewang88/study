package com.dougwag.action.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MikeWang
 * @Date: 2020/6/4 10:26 PM
 * @Description:
 */
public class CfTimeOut {
    public static void main(String[] args) {
        try {
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "A";
            });
            System.out.println(future1.get(1, TimeUnit.SECONDS));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
