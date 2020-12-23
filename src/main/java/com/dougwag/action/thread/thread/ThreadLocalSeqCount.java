package com.dougwag.action.thread.thread;

/**
 * @Author: MikeWang
 * @Date: 2020/9/24 11:34 AM
 * @Description:
 */
public class ThreadLocalSeqCount {
    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    public int nextSeq() {
        seqCount.set(seqCount.get() +1);
        return seqCount.get();
    }

    public static void main(String [] args) {
        ThreadLocalSeqCount seqCount = new ThreadLocalSeqCount();

        SeqThread seqThread1 = new SeqThread(seqCount);
        SeqThread seqThread2 = new SeqThread(seqCount);
        SeqThread seqThread3 = new SeqThread(seqCount);
        SeqThread seqThread4 = new SeqThread(seqCount);

        seqThread1.start();
        seqThread2.start();
        seqThread3.start();
        seqThread4.start();
    }

    public static class SeqThread extends Thread {

        private ThreadLocalSeqCount seqCount;

        public SeqThread(ThreadLocalSeqCount seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i=0; i<3; i++) {
                System.out.println(Thread.currentThread().getName()+" seqCount:"+seqCount.nextSeq());
            }
        }
    }
}