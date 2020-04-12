package com.dougwag.action.juc.myself;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: MikeWang
 * @Date: 2020/3/9 2:13 PM
 * @Description:
 */
public class MyReentrantLock implements Lock {

    private final Mysync mySync;

    public MyReentrantLock(){
        mySync = new Mysync();
    }


    /**
     * 加锁方法
     */
    @Override
    public void lock() {
        this.mySync.acquire(1);
    }

    /**
     * 中断加锁方法
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.mySync.acquireInterruptibly(1);
    }

    /**
     * 尝试加锁
     * @return
     */
    @Override
    public boolean tryLock() {
        return this.mySync.tryAcquire(1);
    }

    /**
     * 在规定的时间内尝试加锁
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return this.mySync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {
        this.mySync.release(1);
    }

    /**
     * 线程条件 里面的方法与Object里面的 wait,notify,notifyAll 等方法类似
     * @return
     */
    @Override
    public Condition newCondition() {
        return this.mySync.newCondition();
    }

    //同时还需要有一个基于AQS的同步器
    //这里我们在上面的类里面写一个内部类来继承AQS 同时重写下面 的三个方法

   private class Mysync extends AbstractQueuedSynchronizer{
        //尝试获取锁
        @Override
        protected boolean tryAcquire(int acquire) {
            //1、获取当前的线程
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName()+"加锁");
            //2、获取当前的线程状态
            int state = getState() ;
            //3、判断状态是不是为0，如果为0那么就是说明当前锁还未被持有，很有可能有其他线程来获取该锁，那么需要进行CAS操作来设置锁的持有状态
            if(state==0 && compareAndSetState(0,acquire)){
                //获取锁成功，将持有线程设置为当前线程
                setExclusiveOwnerThread(thread);
                return true;
                //由于是独占式锁，那么如果是当前线程持有的锁那么就只需要设置状态+1就好了
                //否则就获取不成功
            }else if(thread==getExclusiveOwnerThread()){
                if(state+acquire>=0){
                    setState(state+acquire);
                }
                return true;
            }
            return false;
        }

        //尝试释放锁
        @Override
        protected boolean tryRelease(int release) {
            //释放锁的时候有两点需要注意
            // 1、释放锁的时候肯定是只有持有锁的线程才能来释放锁，所以不需要CAS操作
            //2、释放锁的时候只有当状态为0 的时候才算释放完成
            Thread thread = Thread.currentThread();
            if(thread!=getExclusiveOwnerThread()){
                throw new RuntimeException("非法操作");
            }
            int state = getState()-release;
            boolean flag = false;
            //如果状态为0
            if(state==0){
                //将持有线程置空
                setExclusiveOwnerThread(null);
                flag= true;
            }
            //设置线程状态
            setState(state);
            System.out.println(thread.getName()+"释放");
            return flag;
        }

        //是不是当前线程持有锁
        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread()==Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }
}
