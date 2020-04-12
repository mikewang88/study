package com.dougwag.action.completableFuture.test;



import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by 尼恩 at 疯狂创客圈
 */

public class JavaFutureDemo {

    public static final int SLEEP_GAP = 5000;


    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWarterJob implements Callable<Boolean> //①
    {

        @Override
        public Boolean call() throws Exception //②
        {

            try {
                System.out.println("洗好水壶");
                System.out.println("灌上凉水");
                System.out.println("放在火上");

                //线程睡眠一段时间，代表烧水中
                Thread.sleep(SLEEP_GAP);
                System.out.println("水开了");

            } catch (InterruptedException e) {
                System.out.println(" 发生异常被中断.");
                return false;
            }
            System.out.println("烧水 运行结束.");

            return true;
        }
    }
    static class WashJob implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("洗茶壶");
                System.out.println("洗茶杯");
                System.out.println("拿茶叶");
                //线程睡眠一段时间，代表清洗中
                Thread.sleep(SLEEP_GAP);
                System.out.println("洗完了");

            } catch (InterruptedException e) {
                System.out.println(" 清洗工作 发生异常被中断.");
                return false;
            }
            System.out.println(" 清洗工作  运行结束.");
            return true;
        }
    }

    public static void drinkTea(boolean warterOk, boolean cupOk) {
        if (warterOk && cupOk) {
            System.out.println("泡茶喝");
        } else if (!warterOk) {
            System.out.println("烧水失败，没有茶喝了");
        } else if (!cupOk) {
            System.out.println("杯子洗不了，没有茶喝了");
        }

    }

    public static void main(String args[]) {
        Callable<Boolean> hJob = new HotWarterJob();//③
        FutureTask<Boolean> hTask =
                new FutureTask<>(hJob);//④
        Thread hThread = new Thread(hTask, "** 烧水-Thread");//⑤

        Callable<Boolean> wJob = new WashJob();//③
        FutureTask<Boolean> wTask =
                new FutureTask<>(wJob);//④
        Thread wThread = new Thread(wTask, "$$ 清洗-Thread");//⑤
        hThread.start();
        wThread.start();
        Thread.currentThread().setName("主线程");

        try {

            boolean  warterOk = hTask.get();
            boolean  cupOk = wTask.get();

//            hThread.join();
//            wThread.join();
            drinkTea(warterOk, cupOk);


        } catch (InterruptedException e) {
            System.out.println(getCurThreadName() + "发生异常被中断.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(getCurThreadName() + " 运行结束.");
    }
}