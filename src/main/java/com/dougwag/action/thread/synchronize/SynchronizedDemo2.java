package com.dougwag.action.thread.synchronize;

/**
 * @Author: MikeWang
 * @Date: 2020/3/5 3:20 PM
 * @Description:
 */
public class SynchronizedDemo2 {
    Object object = new Object();
    public void method1(){
        synchronized (object){
            
        }
    }
}
