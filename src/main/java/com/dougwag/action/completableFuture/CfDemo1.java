package com.dougwag.action.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: MikeWang
 * @Date: 2020/6/3 10:19 PM
 * @Description:
 */
public class CfDemo1 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<String> base = new CompletableFuture<>();
        CompletableFuture<String> future =
                base.thenApply(
                        s -> {
                            System.out.println("2");
                            return s + " 2";
                        });
        base.thenAccept(s -> System.out.println(s+"a")).thenAccept(aVoid -> System.out.println("b"));
        base.thenAccept(s -> System.out.println(s+"c")).thenAccept(aVoid -> System.out.println("d"));
        base.complete("1");
        System.out.println("base result: " + base.get());
        System.out.println("future result: " + future.get());
    }
}
