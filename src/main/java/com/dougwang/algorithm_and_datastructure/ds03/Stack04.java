package com.dougwang.algorithm_and_datastructure.ds03;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 9:10 PM
 * @Description:
 * 实现共享栈
 */
public class Stack04 {
}
class BothStackShareMemory {

    private Object[] array;//定义一个数组存储

    private int stackSize;//栈长度

    private int top1;//第一个栈的栈顶指针

    private int top2;//第2个栈的栈顶指针

    /**
     * 初始化构建栈
     */
    public BothStackShareMemory() {
        stackSize = 10;
        array = new Object[stackSize];
        top1 = -1;
        top2 = stackSize;//都是空栈
    }

    /**
     * 压栈
     *
     * @param stackNum
     * @param element
     * @return
     */
    public boolean push(int stackNum, Object element) {
        if (top1 + 1 == top2) {
            System.out.println("栈满");
            return false;
        }
        if (stackNum == 1) {
            top1++;
            array[top1] = element;
        } else {
            top2--;
            array[top2] = element;
        }
        return true;
    }

    /**
     * 弹栈
     *
     * @param stackNum
     * @param element
     * @return
     */
    public boolean pop(int stackNum, Object element) {
        if (top1 == -1 || top2 == stackSize) {
            System.out.println("栈为空");
            return false;
        }

        if(stackNum == 1){
            array[top1] = null;
            top1--;
            return true;
        }else {

            array[top2] = null;
            top2++;
            return true;

        }


    }

    /**
     * 判断是否为空
     *
     * @param i
     * @return
     */
    public boolean isEmpty(int i) {
        if (i == 1) {
            if (top1 == -1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (top2 == stackSize) {
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * 获取栈顶元素
     *
     * @param i
     * @return
     */
    public Object peek(int i) {
        if (i == 1) {
            if(top1 == -1){
                System.out.println("栈为空");
                return null;
            }
            return array[top1];
        } else {
            if(top2 == stackSize){
                System.out.println("栈为空");
                return null;
            }
            return array[top2];
        }
    }


    public static void main(String[] args) {
        BothStackShareMemory memory = new BothStackShareMemory();

        memory.push(1,111);
        memory.push(2,222);
        System.out.println(memory.pop(1,null));
        System.out.println(memory.pop(2,null));
    }
}
