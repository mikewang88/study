//package com.dougwag.action.completableFuture;
//
///**
// * @Author: MikeWang
// * @Date: 2020/9/22 11:00 PM
// * @Description:
// */
//public class MultiThreadTest { /**
// * 初始化4个商店
// */
//List<Shop> shops = Arrays.asList(
//                new Shop("BestPrice"),
//                new Shop("LetsSaveBig"),
//                new Shop("MyFavoriteShop"),
//                new Shop("BuyItAll"));
//    @Test
//    public void test12() {
//        long start = System.nanoTime();
//        List<Double> prices = findPrices("肥皂", shops);
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("同步状态下查询价格耗时" + invocationTime + " ms," + "价格列表:" + prices);
//    }
//
//    public static List<Double> findPrices(String product, List<Shop> shops) {
//        return shops.stream().map(shop -> shop.getPrice(product)).collect(toList());
//    }
//
//    @Test
//    public void test13() {
//        long start = System.nanoTime();
//        List<Future<Double>> pricesFuture = findPricesAsync("肥皂", shops);
//        // 这里可以做其他事情
//        doSomethingElse();
//        List<Double> prices = pricesFuture.stream()
//                .map(doubleFuture -> {
//                    try {
//                        return doubleFuture.get();
//                    } catch (InterruptedException | ExecutionException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .collect(toList());
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("异步状态下查询价格耗时" + invocationTime + " ms," + "价格列表:" + prices);
//    }
//
//    public static List<Future<Double>> findPricesAsync(String product, List<Shop> shops) {
//        return shops.stream().map(shop -> shop.getPriceAsync(product)).collect(toList());
//    }
//
//    private static void doSomethingElse() {
//        // 其他任务...
//    }
//
//    @Test
//    public void test14() {
//        long start = System.nanoTime();
//        List<Double> prices = shops.parallelStream().map(shop -> shop.getPrice("肥皂")).collect(toList());
//        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//        System.out.println("并行流方式查询价格耗时" + invocationTime + " ms," + "价格列表:" + prices);
//    }
//
//}