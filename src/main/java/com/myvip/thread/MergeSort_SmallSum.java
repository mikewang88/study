package com.myvip.thread;

/**
 * @Author: MikeWang
 * @Date: 2021/4/24 10:59 PM
 * @Description: 求小和
 */
  //在一个数组中，一个数左边比它小的数的总和，叫做小和，所有数的小和累加起来，叫做数组的小和。求数组的小和。例如[1, 3, 4, 2, 5]
//1左边比1小的数：没有
//
//    3左边比3小的数：1
//
//    4左边比4小的数：1、3
//
//    2左边比2小的数为：1
//
//    5左边比5小的数为：1、3、4、2
//
//    所以该数组的小和为：1+1+3+1+1+3+4+2 = 16

public class MergeSort_SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    //
    public static int merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = m + 1;
        int res = 0;
        while (p1 <= m && p2 <= r) {
            //不同点：当前的数是比右组小的，产生右组当前位置到右组右边界数量个小和，累加到res。否则res加0
            // why (r - p2 + 1) * arr[p1]? 因为 左右两边都是排好序的，当p2 位置的数比p1 位置的 数大时，
            // 那么就有 (r - p2 + 1) 个数比p1 位置的数大,因为 p1 会向右移动，所以只会比较一次
            // 1  2  3  4  |  0  3  6
            // p1             p2
            // p1                p2 ======>R-P2+1 个数 比 p1 位置的数大
            //    p1             p2 ======>R-P2+1 个数 比 p1 位置的数大
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            // 不同点： 如果p1 位置的数 == p2 位置的数，先copy 右边的数
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

}
