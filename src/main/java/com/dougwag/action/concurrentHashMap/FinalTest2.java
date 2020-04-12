package com.dougwag.action.concurrentHashMap;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: MikeWang
 * @Date: 2020/2/27 11:39 AM
 * @Description:
 */
public class FinalTest2 {
    private final AtomicInteger count = new AtomicInteger();

    public void take() throws InterruptedException {
        System.out.println(this.count.get() == 0);
    }
}
