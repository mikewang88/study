package com.myvip.thread;

/**
 * @Author: MikeWang
 * @Date: 2021/4/13 9:46 PM
 * @Description:
 */
public class InsertSortDemo {

    public static void insertionSort(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        //0-0 是有序的

        for (int i=1;i<arr.length;i++){
            for (int j=i-1;j>=0 &&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }
}
