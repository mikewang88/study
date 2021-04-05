package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/3 2:05 PM
 * @Description:
 * 合并排序的数组
 * 逆向双指针
 */
public class L1001 {
    public void merge(int[] A,int m,int[]B, int n){
        int pa = m-1;
        int pb = n-1;
        int tail = m+n-1;
        int cur ;
        while (pa>=0 ||pb>=0){
            if (pa==-1){
                cur = B[pb--];
            }else if (pb==-1){
                cur = A[pa--];
            }else if (A[pa]>B[pb]){
                cur = A[pa--];
            }else {
                cur =B[pb--];
            }
            A[tail--] =cur;
        }

    }
}
