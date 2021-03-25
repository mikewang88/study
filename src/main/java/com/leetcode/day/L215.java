package com.leetcode.day;

import java.util.PriorityQueue;

/**
 * @Author: MikeWang
 * @Date: 2021/3/24 10:35 AM
 * @Description:
 *
若当前结点为a[k]
则a[k*2+1]，a[k*2+2]是该结点的左子结点和右子结点
a[(k-1)/2]是该结点的父结点。
 */
public class L215 {

    public static void main(String[] args) {
        L215 l215 = new L215();
        int[] nums = new int[]{1,2,3,4,5,6,7,8};
        System.out.println(findKthLargest(nums,3));
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest(nums,3));
    }

    public static int findKthLargest(int[] nums,int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int n:nums){
            heap.add(n);
            if (heap.size()>k){
                heap.poll();
            }
        }
        return heap.peek();
    }

//    public static int findKthLargest2(int[] nums, int k){
//        int heapSize = nums.length - 1;
//        //使数组堆化
//        for (int i=heapSize/2;i>0;i--){
//            sink(nums,i,heapSize);
//        }
//        // 将前k-1个最大的结点下沉至底部，并且不再将其视为堆的一部分
//        // 如此，第k个最大的值自然在堆顶位置，将其返回即可
//        for(int i = 0; i < k ; i++)
//        {
//            exch(nums, 0, heapSize--);
//            sink(nums, 0, heapSize);
//        }
//        return nums[0];
//
//    }
//
//    // 由上至下的堆有序化，通常称之为“下沉”。
//    // 将nums数组中索引为k的结点下沉至合适的位置，使得该结点的值不小于它的两个子结点
//    public static void sink(int[] nums,int k, int heapSize){
//        while (k*2+1<=heapSize){
//            //将j索引到k的两个子结点中较大的那个
//            int j = k * 2 + 1;
//            if(j < heapSize && nums[j] < nums[j + 1])
//                j++;
//
//            //若此时根节点的值已经不小于它的两个子结点，结束循环
//            if (nums[k] >= nums[j])
//                break;
//
//            //交换k, j结点
//            exch(nums, k, j);
//            k = j;
//        }
//    }
//
//    public static void exch(int[] nums,int i,int j){
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }



    static class Solution {
        public int findKthLargest(int[] nums, int k) {
            int heapSize = nums.length;
            buildMaxHeap(nums, heapSize);
            for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
                swap(nums, 0, i);
                --heapSize;
                maxHeapify(nums, 0, heapSize);
            }
            return nums[0];
        }

        public void buildMaxHeap(int[] a, int heapSize) {
            for (int i = heapSize / 2; i >= 0; --i) {
                maxHeapify(a, i, heapSize);
            }
        }

        public void maxHeapify(int[] a, int i, int heapSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largest = i;
            if (l < heapSize && a[l] > a[largest]) {
                largest = l;
            }
            if (r < heapSize && a[r] > a[largest]) {
                largest = r;
            }
            if (largest != i) {
                swap(a, i, largest);//交换数据
                maxHeapify(a, largest, heapSize);
            }
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
