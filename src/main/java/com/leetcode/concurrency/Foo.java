package com.leetcode.concurrency;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 5:37 PM
 * @Description:
 */
public class Foo {
    // 题意需要调用3个方法，后两种分别依赖前两种
    // 那么有依赖关系：1 -> 2 -> 3
    // 此处给出两种原料id，如果有更多的依赖条件就需要考虑更多
    private final int pidOfFirst = 1;
    private final int pidOfSecond = 2;
    // 同时需要有资源锁，可以当做一个生产厂房有限的生产器械，只能同时供一种原料生产或加工
    private final Object resLock = new Object();

    private Stack<Integer> productionChain ;
    public Foo() {
        // Init Resources Pool
        productionChain = new Stack<>();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // 交付给生产产商进行生产，占用工具resLock其他人暂时不得使用
        synchronized (resLock){
            // 生产 原料1
            productionChain.push(pidOfFirst);
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            // 生产完成，通知其他工序开始工作
            resLock.notifyAll();
        }
    }
    public void second(Runnable printSecond) throws InterruptedException {
        // 交付给生产产商进行生产，占用工具resLock其他人暂时不得使用
        synchronized (resLock){
            // 需要先了解资源池是否有需要的原料
            while( productionChain.empty() || productionChain.peek() != pidOfFirst ){
                // 没有原料的话拿到工具也没用，还是坐等原料1吧
                resLock.wait();
            }
            // 确认原料1已提供，从资源池取出原料1
            productionChain.pop();
            // 加工生产原料2
            productionChain.push(pidOfSecond);
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            // 加工完成，通知其他工序开始工作
            resLock.notifyAll();
        }
    }
    public void third(Runnable printThird) throws InterruptedException {
        synchronized (resLock){
            // 由于这里要求是1->2->3，且1和2也要保持顺序，所以只需要判断是否有原料2即可
            while( productionChain.empty() || productionChain.peek() != pidOfSecond ){
                // wait for resource
                resLock.wait();
            }
            // 通过前序工艺加工好的原料进行组装成品
            productionChain.pop();
            // 当然，这里可以改成输出成品等其他操作，但是至少要消耗掉原料2才能继续进行下面的操作
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable a = () -> System.out.println("one");
        Runnable b = () -> System.out.println("two");
        Runnable c = () -> System.out.println("three");

        Foo pl = new Foo();
        pl.first(   a   );
        pl.second(  b   );
        pl.third(   c   );
        // one
        // two
        // three
    }

}
