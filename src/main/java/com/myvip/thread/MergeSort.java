package com.myvip.thread;

/**
 * @Author: MikeWang
 * @Date: 2021/4/24 10:01 PM
 * @Description:
 */
public class MergeSort {
    //递归实现归并排序
    public  static void mergeSort_recursion(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        process(arr,0,arr.length-1);
    }

    //L ...R 位置上是有序的
    private static void process(int[] arr, int L, int R) {
        if (L ==R){
            return;
        }
        //求中间位置
        int mid = L +((R-L) >>1);//防止溢出
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R); //数据合并
    }

    //其实合并的时候有点想 leetcode 中 合并两个有序数组的题目
    private static void merge(int[] arr, int L, int M, int R) {
        //声明一个临时数组
        int[] help =  new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M+1;
        while (p1<=M && p2<=R){//防止数组越界
            //i++ 先赋值再++
            help[i++] = arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        //善后工作,要么p1越界了，要么p2越界了
        while (p1<=M){
            help[i++] =arr[p1++];
        }
        while (p2<=R){
            help[i++] =arr[p2++];
        }
        for (i=0;i<help.length;i++){
            arr[L+i] = help[i];
        }
    }

    // 非递归方法实现
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        int N = arr.length;
        //步长
        int mergeSize = 1;
        while (mergeSize<N){
            //当前左组的第一个位置
            int L = 0;
            while (L<N){
                if (mergeSize>=N-L){//防止越界
                    break;
                }
                int M = L+mergeSize-1;
                int R = M+Math.min(mergeSize,N-M-1);
                merge(arr,L,M,R);
            }
            //防止溢出
            if (mergeSize> N/2){
                break;
            }
            mergeSize<<=1;//扩容2倍
        }
    }
}
