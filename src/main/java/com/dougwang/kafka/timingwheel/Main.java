package com.dougwang.kafka.timingwheel;

import com.dougwang.kafka.timingwheel.util.MyThread;
import com.dougwang.kafka.timingwheel.util.Timer;
import com.dougwang.kafka.timingwheel.util.TimerTask;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: MikeWang
 * @Date: 2020/6/4 5:41 PM
 * @Description:
 */
//时间轮是一种环形数据结构，类似于时钟，秒针、分针、时针分别为一层，每层分成多个格子，每个格子中存放任务集合，一个单独的线程推进时间一格一格的移动，并执行格子中的任务。
//        TimingWheel并非简单的环形时间轮，而是多层级时间轮，每个时间轮由多个时间格组成，每个时间格为一个时间间隔，底层的时间格跨度较小，然后随着延迟任务延迟时间的长短逐层变大；
//        如下图，下层的时间轮每个时间格为1ms，整个时间轮为10ms，而上面一层的时间轮中时间格为10ms，整个时间轮为100ms，
//        上级时间轮添加的规则为：当前currentTime为上级时间轮的startMs，当前interval为上级时间轮的tickDuration，ticksPerWheel相同；
//        简单点说就是上层时间轮跨度为当前的M倍，时间格为当前的N倍；
//        时间轮常用于延时任务，在Netty、akka、Quartz、Zookeeper等高性能组件中都存在时间轮定时器的踪影。

//kafka时间轮
//在Kafka中应用了大量的延迟操作，但在Kafka中 并没用使用JDK自带的Timer或是DelayQueue用于延迟操作，而是使用自己开发的DelayedOperationPurgatory组件用于管理延迟操作，
//Kafka这类分布式框架有大量延迟操作并且对性能要求及高，而java.util.Timer与java.util.concurrent.DelayQueue的插入和删除复杂度都为对数阶O(log n)并不能满足Kafka性能要求，
//所以Kafka实现了基于时间轮的定时任务组件，该时间轮定时任务实现的插入与删除（开始定时器与暂停定时器）的时间复杂度都为常数阶O(1)。

public class Main {
    public static void main(String[] args) throws Exception{
        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask(3000,()->{
//            System.out.println("1----------执行");
//        });
//        timer.addTask(timerTask);
////        TimerTask timerTask2 = new TimerTask(60000,()->{
////            System.out.println("2----------执行");
////        });
////        timer.addTask(timerTask2);

        CompletableFuture<String> base = new CompletableFuture<>();
        base.thenApply(
                    s -> {
                        System.out.println("2");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return s + " 2";
                    });
        TimerTask timerTask2 = new TimerTask(3000, new MyThread(base));
        timer.addTask(timerTask2);
        System.out.println("===================");
        System.out.println("base.get==="+base.get());

    }

//    static class MyThread implements Runnable{
//        CompletableFuture<String> cf;
//        public MyThread(CompletableFuture<String>  cf){
//            this.cf = cf;
//        }
//        public void run(){
//            cf.thenApply(
//                    s -> {
//                        System.out.println("2");
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        return s + " 2";
//                    });
//            cf.complete("1");
//        }
//    }
}
