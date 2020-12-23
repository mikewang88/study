package com.dougwang.algorithm_and_datastructure.ds04;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:19 PM
 * @Description:
 * 滑动窗口
 * https://zhuanlan.zhihu.com/p/63276757
 */
public class Queue03 {
}

class Solution {
    public static ArrayList<Integer> maxInWindows2(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num == null || num.length < size || size < 1) {
            return ret;
        }

        LinkedList<Integer> indexDeque = new LinkedList<>();
        // 初始化第一个滑动窗口的数值
        for (int i = 0; i < size - 1; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            // 双端队列保存当前值的索引
            indexDeque.addLast(i);
        }
        // 处理其他的滑动窗口
        for (int i = size - 1; i < num.length; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            // 如果最大值不在滑动窗口时，进行移除
            if (i - indexDeque.getFirst() + 1 > size) {
                indexDeque.removeFirst();
            }
            // 将结果数组里添加队列第一个元素（即当前滑动窗口最大值）
            ret.add(num[indexDeque.getFirst()]);
        }
        return ret;
    }

    //这个好理解
    public static ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        // 排除特殊情况，窗口的长度为0
        if (size==0) return result;

        // 滑动窗口最左边数的index
        int begin;
        // 建立一个双端队列 存数组下标
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=0;i<num.length;i++){
            // begin是窗口起始位置
            begin = i-size+1;
            // 队列空，直接加入
            if(q.isEmpty())
                q.add(i);
                // 若队列最左边值已经不在窗口内，直接删除
            else if(begin > q.peekFirst())
                q.pollFirst();

            // 从队尾开始比较，把所有比他小的值丢掉
            while((!q.isEmpty()) && num[q.peekLast()] <= num[i])
                q.pollLast();
            // 随后再把它放进去
            q.add(i);

            // 若窗口起始位置在数组的0位置上或者之后（窗口是完整大小的），才计算窗口的有效最大值
            if(begin>=0){
                // 永远是队列最左边最大，加入结果集
                result.add(num[q.peekFirst()]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] num = new int[]{2,3,4,2,6,2,5,1};
        ArrayList<Integer> list = maxInWindows(num,3);
        for (Integer max:list){
            System.out.println(max);
            //System.out.println(num[max]);
        }
    }
}
