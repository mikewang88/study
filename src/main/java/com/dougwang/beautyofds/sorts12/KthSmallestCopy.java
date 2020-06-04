package com.dougwang.beautyofds.sorts12;

/**
 * @Author: MikeWang
 * @Date: 2020/5/19 5:07 PM
 * @Description:
 */
public class KthSmallestCopy {
    private static void swap(int[] arr, int left,int right){
        if (left == right) {
            return;
        }
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static int kthSmallest(int[] arr, int k){
        if (arr == null || arr.length<k){
            return -1;
        }
        int partition = partition(arr,0,arr.length-1);
        while (partition + 1 != k) {
            if (partition + 1 < k) {
                partition = partition(arr, partition + 1, arr.length - 1);
            } else {
                partition = partition(arr, 0, partition - 1);
            }
        }

        return arr[partition];
    }

    private static int partition(int[] arr,int left, int right){
        int pivot = arr[right];
        int i =left;
        for (int j=left;j<right;j++){
            // 这里要是 <= ，不然会出现死循环，比如查找数组 [1,1,2] 的第二小的元素
            if (arr[j]<=pivot){
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);

        return i;
    }



    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6};
        System.out.println(kthSmallest(a,1));
    }
}
