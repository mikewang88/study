package com.dougwag.action.thread.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: MikeWang
 * @Date: 2020/1/11 2:41 PM
 * @Description:
 */
public class SleepUtils {
public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
