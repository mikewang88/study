package com.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @Author: MikeWang
 * @Date: 2020/7/17 5:42 PM
 * @Description:
 */
public class DelayQueueTest {
    private static DelayQueue delayQueue = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {

                delayQueue.offer(new MyDelayedTask("task1", 10000));
                delayQueue.offer(new MyDelayedTask("task2", 3900));
                delayQueue.offer(new MyDelayedTask("task3", 1900));
                delayQueue.offer(new MyDelayedTask("task4", 5900));
                delayQueue.offer(new MyDelayedTask("task5", 6900));
                delayQueue.offer(new MyDelayedTask("task6", 7900));
                delayQueue.offer(new MyDelayedTask("task7", 4900));

            }
        }).start();

        while (true) {
            Delayed take = delayQueue.take();
            System.out.println(take);
        }
    }
}
