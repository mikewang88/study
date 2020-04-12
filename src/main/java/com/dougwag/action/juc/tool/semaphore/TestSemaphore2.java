package com.dougwag.action.juc.tool.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MikeWang
 * @Date: 2020/3/10 2:41 PM
 * @Description:
 */
public class TestSemaphore2 {
    public static void main(String[] args) {
        int permitsNum = 2;
        final Semaphore semaphore = new Semaphore(permitsNum);
        try {
            System.out.println("availablePermits:" + semaphore.availablePermits() + ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
            semaphore.release();
            System.out.println("availablePermits:" + semaphore.availablePermits() + ",semaphore.tryAcquire(3,1, TimeUnit.SECONDS):" + semaphore.tryAcquire(3, 1, TimeUnit.SECONDS));
            semaphore.release();
            semaphore.release();
            semaphore.release();
            semaphore.release();
            System.out.println("availablePermits:" + semaphore.availablePermits() + ",semaphore.tryAcquire(5,1, TimeUnit.SECONDS):" + semaphore.tryAcquire(5, 1, TimeUnit.SECONDS));

        } catch (Exception e) {

        }
    }
}
