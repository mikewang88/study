package com.dougwag.action.juc.myself;

/**
 * @Author: MikeWang
 * @Date: 2020/3/9 4:32 PM
 * @Description:
 *
 *
 * 1 定义一个读写锁共享变量state
2 state高16位为读锁数量，低16位为写锁数量。尽量模拟ReentrantReadWriteLock的实现
3 获取读锁时先判断是否有写锁，有则等待，没有则将读锁数量加1
4 释放读锁数量减1，通知所有等待线程
5 获取写锁时需要判断读锁和写锁是否都存在，有则等待，没有则将写锁数量加1
6 释放写锁数量减1，通知所有等待线程
我给出的实现代码如下：

作者：knock_小新
链接：https://juejin.im/post/5b9df6015188255c8f06923a
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MyReadWriteLock {
    //定义一个读写锁共享变量state
    private int state =0;
    // 2. state高16位为读锁数量
    private int GetReadCount(){
        return state >>> 16;
    }
    // 2. state低16位为写锁数量
    private int GetWriteCount(){
        return state & ((1 << 16) - 1);
    }
    //3. 获取读锁时先判断是否有写锁，有则等待，没有则将读锁数量加1
    public synchronized void lockRead() throws InterruptedException{

        while (GetWriteCount() > 0) {
            wait();
        }

        System.out.println("lockRead ---" + Thread.currentThread().getName());
        state = state + (1 << 16);
    }

    //4. 释放读锁数量减1，通知所有等待线程
    public synchronized void unlockRead() {
        state = state - ((1 << 16));
        notifyAll();
    }

    //5. 获取写锁时需要判断读锁和写锁是否都存在，有则等待，没有则将写锁数量加1
    public synchronized void lockWriters() throws InterruptedException{

        while (GetReadCount() > 0 || GetWriteCount() > 0) {
            wait();
        }
        System.out.println("lockWriters ---" + Thread.currentThread().getName());
        state++;
    }

    //6. 释放写锁数量减1，通知所有等待线程
    public synchronized void unlockWriters(){

        state--;
        notifyAll();
    }
}

