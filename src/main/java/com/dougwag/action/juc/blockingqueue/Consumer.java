package com.dougwag.action.juc.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: MikeWang
 * @Date: 2020/3/9 7:22 PM
 * @Description:
 */
public class Consumer implements Runnable {

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
