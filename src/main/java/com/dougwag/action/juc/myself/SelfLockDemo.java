package com.dougwag.action.juc.myself;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: MikeWang
 * @Date: 2020/3/9 2:29 PM
 * @Description:
 */
public class SelfLockDemo {
    public static void main(String[] args) {
        final MyReentrantLock reentrantLock  = new MyReentrantLock();
        final List<Integer> list = new ArrayList<>();
        final CountDownLatch countDownLatch = new CountDownLatch(100);
//        final AtomicReference<MyInteger> atomicI = new AtomicReference<MyInteger>(new MyInteger(4999));
        for (int i = 0; i < 5000; i++) {
            list.add(i);
        }
        final MyInteger j = new MyInteger(4999);
        /*定义线程工厂 方便bug回溯追踪*/
        ThreadFactory customThreadfactory = new ThreadFactoryBuilder()
                .setNameFormat("测试-Thread").setDaemon(false)
                .setPriority(Thread.MAX_PRIORITY).build();
        /*keepAliveTile 线程最大空闲时间 */
        ExecutorService executorService =
                new ThreadPoolExecutor(100, 200, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),customThreadfactory);
        long b = System.currentTimeMillis();
        for(int i=0;i<100 ;i++){
            executorService.execute(() -> {
                        while (true) {
                            //   int j = atomicInteger.getAndDecrement();
                            if (j.getIndex() >= 0) {
                                reentrantLock.lock();
                                System.out.println("线程"+Thread.currentThread().getName()+"====》" +list.get(j.getIndex()));
                                j.setIndex(j.getIndex()-1);
                                reentrantLock.unlock();
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                break;
                            }
                        }
                        countDownLatch.countDown();
                    }
            );
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时---->" + (System.currentTimeMillis() - b));
        executorService.shutdown();

    }

}

class MyInteger{
    int index;
    public MyInteger(int i){
        this.index=i;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
