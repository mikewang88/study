package com.dougwag.action.juc.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Author: MikeWang
 * @Date: 2020/3/9 7:22 PM
 * @Description:
 */
public class Producer implements Runnable {

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            queue.put("1");
            Thread.sleep(1000);
            queue.put("2");
            Thread.sleep(1000);
            queue.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
