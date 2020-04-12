package com.dougwang.algorithm_and_datastructure.ds03;

import java.util.Stack;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:10 PM
 * @Description:
 * 实现一个栈，入栈push 出栈 pop ，返回最小值min的复杂度 O(1)
 * https://blog.csdn.net/u010452388/article/details/81665826
 */
public class Stack01 {
    public static void main(String[] args) {
        GetMinStack stack = new GetMinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.getMin());
    }
}


class GetMinStack {
    //声明一个data栈
    private Stack<Integer> dataStack;
    //声明一个min栈
    private Stack<Integer> minStack;

    //无参构造函数
    public GetMinStack() {
        this.dataStack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }

    //入栈
    public void push(int num) {
        //1.入栈有两种情况
        //1.1 栈为空，那么第一个数直接进data栈和min栈
        //1.2 栈不为空，data栈正常入，入min栈的时候，先与min栈的栈顶数据进行比较，小的数入min栈
        if (dataStack.isEmpty()) {//栈为空
            dataStack.push(num);
            minStack.push(num);
        } else {//栈不为空
            dataStack.push(num);
            if (num < minStack.peek()) {
                minStack.push(num);
            } else {
                minStack.push(minStack.peek());
            }
        }
    }

    //弹栈
    public Integer pop() {
        //弹栈的时候，data栈和min栈一起弹出，如果栈为空，则先提醒用户
        if (dataStack.isEmpty()) {
            throw new IllegalArgumentException("栈已空");
        }
        minStack.pop();
        return dataStack.pop();
    }

    //取最小值
    public Integer getMin() {
        //直接获取最小栈的数据即可，如果栈为空，则先提醒用户
        if (dataStack.isEmpty()) {
            throw new IllegalArgumentException("栈已空");
        }
        return minStack.peek();
    }

}
