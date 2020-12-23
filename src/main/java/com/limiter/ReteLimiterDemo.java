package com.limiter;


import com.google.common.util.concurrent.RateLimiter;

/**
 * @Author: MikeWang
 * @Date: 2020/6/18 2:54 PM
 * @Description:
 */
public class ReteLimiterDemo {
    static RateLimiter limiter = RateLimiter.create(2);

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
//            limiter.acquire();
            if (!limiter.tryAcquire()){
                continue;
            }
            new Thread(new Task()).start();
        }
    }
}
