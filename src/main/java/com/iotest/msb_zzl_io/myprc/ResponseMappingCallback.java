//package com.iotest.msb_zzl_io.myprc;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Author: MikeWang
// * @Date: 2020/11/8 6:05 PM
// * @Description:
// */
//public class ResponseMappingCallback {
//    static ConcurrentHashMap<Long, CompletableFuture> mapping = new ConcurrentHashMap<>();
//
//    public static void addCallBack(long requestID, CompletableFuture cb) {
//        mapping.putIfAbsent(requestID, cb);
//    }
//
//    public static void runCallBack(MyRPCTest.Packmsg msg) {
//        CompletableFuture cf = mapping.get(msg.header.getRequestID());
//        // runnable.run();
//        cf.complete(msg.getContent().getRes());
//        removeCB(msg.header.getRequestID());
//    }
//
//    private static void removeCB(long requestID) {
//        mapping.remove(requestID);
//    }
//
//}
