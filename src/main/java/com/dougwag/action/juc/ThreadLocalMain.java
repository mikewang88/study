package com.dougwag.action.juc;

/**
 * @Author: MikeWang
 * @Date: 2020/7/20 3:29 PM
 * @Description:
 */
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h3>Exper1</h3>
 * <p>ThreadLocalId</p>
 *
 * @author : cxc
 * @date : 2020-04-01 23:48
 **/
class ThreadLocalId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal <Integer> threadId =
            ThreadLocal.withInitial(() -> nextId.getAndIncrement());

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
    public static void remove() {
        threadId.remove();
    }
}

/**
 * <h3>Exper1</h3>
 * <p></p>
 *
 * @author : cxc
 * @date : 2020-04-02 00:07
 **/
public class ThreadLocalMain {
    private static void incrementSameThreadId(){
        try{
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread()
                        +"_"+i+",threadId:"+
                        ThreadLocalId.get());
            }
        }finally {
            ThreadLocalId.remove();
        }
    }

    public static void main(String[] args) {
        incrementSameThreadId();
        new Thread(() -> incrementSameThreadId()).start();
        new Thread(() -> incrementSameThreadId()).start();
    }
}
