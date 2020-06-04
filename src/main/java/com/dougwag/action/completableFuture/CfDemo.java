package com.dougwag.action.completableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: MikeWang
 * @Date: 2020/3/3 10:41 AM
 * @Description:
 */
public class CfDemo {
    public static void main(String[] args) {
        acceptEither();
    }

    public static void completableDemo(){
        CompletableFuture<String> future  = CompletableFuture.supplyAsync(() -> "Hello");

        future.complete("World");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void completableDemo2(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.complete("World");

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void completeExceptionally(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        future.completeExceptionally(new Exception());

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void thenApply(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World").thenApply(String::toUpperCase);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void whenComplete(){
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s->s+" World")
                .thenApply(s->s+ "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete((result, throwable) -> System.out.println(result));
        //whenComplete 消费类型
//        CompletableFuture.supplyAsync(() -> "hello world")
//                .thenApply(s -> {
//                    s = null;
//                    int length = s.length();
//                    return length;
//                }).thenAccept(i -> System.out.println(i))
//                .whenComplete((result, throwable) -> {
//
//                    if (throwable != null) {
//                        System.out.println("Unexpected error:"+throwable);
//                    } else {
//                        System.out.println(result);
//                    }
//
//                });

    }

    public static void handle(){
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> 100)
                .thenApply(s->s/0)
                .handle((s, t) -> s != null ? Double.valueOf(s) : 0);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void acceptEither(){
        Random random = new Random();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future2";
        });

        CompletableFuture<Void> future =  future1.acceptEither(future2,str->System.out.println("The future is "+str));

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void applyToEither(){
        Random random = new Random();

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from future2";
        });

        CompletableFuture<String> future =  future1.applyToEither(future2,str->"The future is "+str);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void allOf(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "tony");

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "cafei");

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "aaron");

        CompletableFuture.allOf(future1, future2, future3)
                .thenApply(v ->
                        Stream.of(future1, future2, future3)
                                .map(CompletableFuture::join)
                                .collect(Collectors.joining(" ")))
                .thenAccept(System.out::print);
    }


    public static void allOfList(){
        ExecutorService exector = Executors.newFixedThreadPool(5);
        List<List<String>> result = new ArrayList<>();
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        List<String> testList2 = new ArrayList<>();
        testList2.add("4");
        testList2.add("5");
        result.add(testList);
        result.add(testList2);

        List<CompletableFuture<List<String>>> futures = result.stream().map(
                key->CompletableFuture.supplyAsync(
                        ()->deallist(key),exector)).collect(Collectors.toList());

        result = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result);


    }

    public static List<String> deallist(List<String> testList){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testList.add("aaa");
        return testList;
    }




    public static void anyOf(){
        Random rand = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "from future3";
        });

        CompletableFuture<Object> future =  CompletableFuture.anyOf(future1,future2,future3);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void exceptionally(){
        CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(s -> {
                    s = null;
                    int length = s.length();
                    return length;
                }).thenAccept(i -> System.out.println(i))
                .exceptionally(t -> {
                    System.out.println("Unexpected error:" + t);
                    return null;
                });
    }
}
