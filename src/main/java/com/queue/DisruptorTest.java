package com.queue;

/**
 * @Author: MikeWang
 * @Date: 2020/6/27 10:19 AM
 * @Description:
 */
public class DisruptorTest {
    public static void main(String[] args) {
        Queue queue =  new Queue(4);
        queue.add(1000L);

    }
}
class Queue {
    private Long[] data;
    private int size = 0, head = 0, tail = 0;
    public Queue(int size) {
        this.data = new Long[size];
        this.size = size;
    }

    public boolean add(Long element) {
        if ((tail + 1) % size == head) return false;
        data[tail] = element;
        tail = (tail + 1) % size;
        return true;
    }

    public Long poll() {
        if (head == tail) return null;
        long ret = data[head];
        head = (head + 1) % size;
        return ret;
    }
}
