package com.dougwang.algorithm_and_datastructure.ds03;

import java.util.Stack;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:10 PM
 * @Description:
 * 不借助额外空间实现栈的逆序
 * https://blog.csdn.net/qq_27161673/article/details/50880735
 */
public class Stack03 {
}

class ReverseStack {
    //获取并删除栈底元素
    public static int getBottomElement(Stack<Integer> stack) {
        int res = stack.pop();

        if (stack.isEmpty()) {
            return res;
        } else {
            int bottom = getBottomElement(stack);
            stack.push(res);
            return bottom;
        }
    }

    //利用递归反转栈
    public static void reverse(Stack<Integer> stack) {
        //基准条件：如果栈内只剩一个元素，直接返回即可
        if (stack.size() == 1)
            return;

        //获取栈底元素
        int bottom = getBottomElement(stack);

        //进行递归，目的是利用系统栈帧将bottom进行存储
        reverse(stack);

        stack.push(bottom);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}


