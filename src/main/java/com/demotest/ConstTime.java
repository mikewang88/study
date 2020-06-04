package com.demotest;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @Author: MikeWang
 * @Date: 2020/5/26 3:45 PM
 * @Description:
 */
public class ConstTime {
    public static void main(String[] args) {
        try {
            testStopWatch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void testStopWatch() throws InterruptedException {
        System.out.println("SLAMonitorThread.main() start");
        StopWatch sw = new StopWatch();
        sw.start();
        TimeUnit.SECONDS.sleep(1L);
        sw.split();
        System.out.println(
                "SLAMonitorThread.main() end. split:" + sw.getSplitTime() + ", " + sw.toSplitString());
        TimeUnit.SECONDS.sleep(1L);
        sw.split();
        System.out.println(
                "SLAMonitorThread.main() end. split:" + sw.getSplitTime() + ", " + sw.toSplitString());
        TimeUnit.SECONDS.sleep(1L);
        sw.split();
        System.out.println(
                "SLAMonitorThread.main() end. split:" + sw.getSplitTime() + ", " + sw.toSplitString());
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("SLAMonitorThread.main() end. end:" + sw.getTime() + ", " + sw.toString());

        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("it consumes " + (System.currentTimeMillis() - start) + "ms");
        /*
         * SLAMonitorThread.main() start
         * SLAMonitorThread.main() end. split:1005, 0:00:01.005
         * SLAMonitorThread.main() end. split:2016, 0:00:02.016
         * SLAMonitorThread.main() end. split:3021, 0:00:03.021
         * SLAMonitorThread.main() end. end:4025, 0:00:04.025
         * it consumes 1004ms
         */
    }
}
