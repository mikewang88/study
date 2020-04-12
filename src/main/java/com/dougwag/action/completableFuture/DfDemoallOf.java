package com.dougwag.action.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: MikeWang
 * @Date: 2020/3/3 1:41 PM
 * @Description:
 */
public class DfDemoallOf {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "A";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "cafei");

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "aaron");

//        CompletableFuture.allOf(future1, future2, future3)
//                .thenApply(v ->
//                Stream.of(future1, future2, future3)
//                        .map(CompletableFuture::join)
//                        .collect(Collectors.joining(" ")))
//                .thenAccept(System.out::print).exceptionally(t -> {
//            System.out.println("Unexpected error:" + t);
//            return null;
//        });
        try {
            System.out.println(CompletableFuture.allOf(future1, future2, future3).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
