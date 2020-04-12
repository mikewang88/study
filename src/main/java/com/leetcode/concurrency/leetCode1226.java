package com.leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: MikeWang
 * @Date: 2020/3/24 2:54 PM
 * @Description:
 *  哲学家进餐
 *  https://messi1002.top/2020/03/01/LeetCode-1226-%E5%93%B2%E5%AD%A6%E5%AE%B6%E8%BF%9B%E9%A4%90%EF%BC%88Medium%EF%BC%89Java%E8%AF%AD%E8%A8%80%E9%A2%98%E8%A7%A3/
 */
public class leetCode1226 {
}
// 1.synchronized+改变一个哲学家拿叉子的顺序来避免死锁
class DiningPhilosophers {
    private Object[] locks = new Object[5];

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            locks[i] = new Object();
        }
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // 改变0号哲学家拿叉子的顺序
        int leftForkNumber = philosopher == 0? (philosopher + 1) % 5: philosopher;
        int rightForkNumber = philosopher == 0? philosopher: (philosopher + 1) % 5;
        synchronized (locks[leftForkNumber]) {
            synchronized (locks[rightForkNumber]) {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }
    }
}

// 2.lock+改变一个哲学家拿叉子的顺序来避免死锁
class DiningPhilosophers2 {
    private Lock[] locks = {new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};

    public DiningPhilosophers2() {
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        // 改变0号哲学家拿叉子的顺序
        int leftForkNumber = philosopher == 0? (philosopher + 1) % 5: philosopher;
        int rightForkNumber = philosopher == 0? philosopher: (philosopher + 1) % 5;
        locks[leftForkNumber].lock();
        locks[rightForkNumber].lock();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        locks[rightForkNumber].unlock();
        locks[leftForkNumber].unlock();
    }
}

// 3.synchronized+信号量+餐票策略来避免死锁
class DiningPhilosophers3 {
    private Object[] locks = new Object[5];
    private Semaphore limit = new Semaphore(3);

    public DiningPhilosophers3() {
        for (int i = 0; i < 5; i++) {
            locks[i] = new Object();
        }
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftForkNumber = philosopher;
        int rightForkNumber = (philosopher + 1) % 5;
        // 规定最多有三个哲学家同时拿起叉子
        limit.acquire();
        synchronized (locks[leftForkNumber]) {
            synchronized (locks[rightForkNumber]) {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }
        limit.release();
    }
}

// 4.lock+信号量+餐票策略来避免死锁
class DiningPhilosophers5 {
    private Lock[] locks = {new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};
    private Semaphore limit = new Semaphore(3);

    public DiningPhilosophers5() {
    }

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int leftForkNumber = philosopher;
        int rightForkNumber = (philosopher + 1) % 5;
        // 规定最多有三个哲学家同时拿起叉子
        limit.acquire();
        locks[leftForkNumber].lock();
        locks[rightForkNumber].lock();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        locks[rightForkNumber].unlock();
        locks[leftForkNumber].unlock();
        limit.release();
    }
}
