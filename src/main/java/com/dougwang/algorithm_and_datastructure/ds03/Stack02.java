package com.dougwang.algorithm_and_datastructure.ds03;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:10 PM
 * @Description:
 * 使用两个队列实现一个栈
 * https://blog.csdn.net/super_YC/article/details/75208174
 */
public class Stack02 {
}
/**
 *
 * @author superYC
 * 两个队列实现一个栈
 *
 */
class TwoQueueImplStack {
    Queue<Integer> queue1 = new ArrayDeque<Integer>();
    Queue<Integer> queue2 = new ArrayDeque<Integer>();

    /*
     * 向栈中压入数据
     */
    public void push(Integer element){
        //两个队列都为空时，优先考虑 queue1
        if(queue1.isEmpty() && queue2.isEmpty()){
            queue1.add(element);
            return;
        }

        //如果queue1为空，queue2有数据，直接放入queue2
        if(queue1.isEmpty()){
            queue2.add(element);
            return;
        }

        //如果queue2为空，queue1有数据，直接放入queue1中
        if(queue2.isEmpty()){
            queue1.add(element);
            return;
        }
    }

    /*
     * 从栈中弹出一个数据
     */
    public Integer pop(){
        //如果两个栈都为空，则没有元素可以弹出，异常
        if(queue1.isEmpty() && queue2.isEmpty()){
            try{
                throw new Exception("satck is empty!");
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //如果queue1中没有元素，queue2中有元素，将其queue2中的元素依次放入queue1中，直到最后一个元素，弹出即可
        if(queue1.isEmpty()){
            while(queue2.size() > 1){
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }

        //如果queue2中没有元素，queue1中有元素，将其queue1中的元素依次放入queue2中，直到最后一个元素，弹出即可
        if(queue2.isEmpty()){
            while(queue1.size() > 1){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }

        return null;
    }

    public static void main(String[] args) {
        TwoQueueImplStack qs = new TwoQueueImplStack();
        qs.push(2);
        qs.push(4);
        qs.push(7);
        qs.push(5);
        System.out.println(qs.pop());
        System.out.println(qs.pop());

        qs.push(1);
        System.out.println(qs.pop());
    }
}

/**
 *
 * @author superYC
 * 两个栈实现一个队列
 *
 */
class TwoStackImplQueue {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /*
     * 队列的数据压入过程
     */
    public void push(Integer element){
        stack1.push(element);
    }

    /*
     * 队列的数据弹出过程
     */
    public Integer pop(){
        if(stack2.size() <= 0){	//第二个栈为空
            while(stack1.size() > 0){	//第一个栈不为空
                stack2.push(stack1.pop());	//将其第一个栈的数据压入第二个栈中
            }
        }
        if(stack2.isEmpty()){
            try{
                throw new Exception("queue is empty");
            }catch(Exception e){
                //e.printStackTrace();
            }
        }
        Integer head = stack2.pop();
        return head;
    }

    public static void main(String[] args) {
        TwoStackImplQueue sq = new TwoStackImplQueue();
        sq.push(1);
        sq.push(3);
        sq.push(5);
        sq.push(4);
        sq.push(2);

        System.out.println(sq.pop());
        System.out.println(sq.pop());

        sq.push(7);
        System.out.println(sq.pop());
    }
}