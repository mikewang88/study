package com.dougwag.action.concurrentHashMap;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: MikeWang
 * @Date: 2020/2/27 11:14 AM
 * @Description:
 */
public class FinalTest {
    private final AtomicInteger count = new AtomicInteger();

    public void take() throws InterruptedException {
        final AtomicInteger count = this.count;
        System.out.println(count.get() == 0);
    }
}
