package com.dougwag.action.juc.threadpoolexecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: MikeWang
 * @Date: 2020/3/10 10:26 AM
 * @Description:
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " is rejected");
        System.out.println(r.toString() + " is rejected");
    }

}
