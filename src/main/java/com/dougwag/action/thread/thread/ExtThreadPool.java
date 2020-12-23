package com.dougwag.action.thread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MikeWang
 * @Date: 2020/6/18 4:24 PM
 * @Description: 扩展线程池
 */
public class ExtThreadPool {
    public static  class MyTask implements Runnable{
        public String name;
        public MyTask(String name){
            this.name =name;
        }

        @Override
        public void run(){
            System.out.println("正在执行"+"：Thread Id："+Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("start:"+((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("over:"+((MyTask)r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("thread pool exit");
            }
        };

        for (int i=0;i<5;i++){
            MyTask task = new MyTask("task-geym-"+i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
