package com.dougwag.action.thread.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: MikeWang
 * @Date: 2020/1/6 1:57 PM
 * @Description:
 */
public class CompletionTest {
    public static void main(String[] args) {
        CompletionTest test = new CompletionTest();
        System.out.println(test.getNum().toString());
    }

    public Integer getNum() {
// 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
// 创建 CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
// 用于保存 Future 对象
        List<Future<Integer>> futures = new ArrayList<>(3);
// 提交异步任务，并保存 future 到 futures
        futures.add(cs.submit(() -> geocoderByS1()));
        futures.add(cs.submit(() -> geocoderByS2()));
        futures.add(cs.submit(() -> geocoderByS3()));
// 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则 break
            for (int i = 0; i < 3; ++i) {
                try {
                    try {
                        r = cs.take().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                // 简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } finally {
            // 取消所有任务
            for (Future<Integer> f : futures)
                f.cancel(true);
        }
// 返回结果
        return r;
    }

    public Integer geocoderByS1(){
        try {
            Thread.sleep(100*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public Integer geocoderByS2(){
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public Integer geocoderByS3(){
        try {
            Thread.sleep(30*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 3;
    }
}
