package com.dougwang.algorithm_and_datastructure.ds05;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:23 PM
 * @Description:
 * 动态数据求中位数
 * https://www.liwei.party/2019/06/11/leetcode-solution-new/find-median-from-data-stream/
 */

public class Heap03 {
}

class MedianFinder {

    /**
     * 当前大顶堆和小顶堆的元素个数之和
     */
    private int count;
    private PriorityQueue<Integer> maxheap;
    private PriorityQueue<Integer> minheap;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        count = 0;
        maxheap = new PriorityQueue<>((x, y) -> y - x);
        minheap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        count += 1;
        maxheap.add(num);
        minheap.add(maxheap.poll());
        // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
        if ((count & 1) != 0) {
            maxheap.add(minheap.poll());
        }
    }

    public double findMedian() {
        if ((count & 1) == 0) {
            // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
            return (double) (maxheap.peek() + minheap.peek()) / 2;
        } else {
            // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
            return (double) maxheap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        list.forEach(e->medianFinder.addNum(e));
        System.out.println(medianFinder.findMedian());
    }
}
