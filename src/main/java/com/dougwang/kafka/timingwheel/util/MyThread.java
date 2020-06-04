package com.dougwang.kafka.timingwheel.util;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: MikeWang
 * @Date: 2020/6/4 8:37 PM
 * @Description:
 */
public class MyThread implements Runnable{
    CompletableFuture<String> cf;
    public MyThread(CompletableFuture<String>  cf){
        this.cf = cf;
    }
    public void run(){
        cf.complete("1");
    }
}
