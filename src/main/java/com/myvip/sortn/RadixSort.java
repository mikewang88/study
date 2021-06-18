package com.myvip.sortn;

/**
 * @Author: MikeWang
 * @Date: 2021/5/9 12:38 PM
 * @Description:
 */
/**

 * 基数排序

 * 平均O(d(n+r)),最好O(d(n+r)),最坏O(d(n+r));空间复杂度O(n+r);稳定;较复杂

 * d为位数,r为分配后链表的个数

 * @author zeng

 *

 */

public class RadixSort {
//pos=1表示个位，pos=2表示十位

    public static int getNumInPos(int num, int pos) {
        int tmp = 1;

        for (int i = 0; i < pos - 1; i++) {
            tmp *= 10;

        }

        return (num / tmp) % 10;

    }

//求得最大位数d

    public static int getMaxWeishu(int[] a) {
        int max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (a[i] > max)

                max = a[i];

        }

        int tmp = 1, d = 1;

        while (true) {
            tmp *= 10;

            if (max / tmp != 0) {
                d++;

            } else

                break;

        }

        return d;

    }

    public static void radixSort(int[] a, int d) {
        int[][] array = new int[10][a.length + 1];

        for (int i = 0; i < 10; i++) {
            array[i][0] = 0;

// array[i][0]记录第i行数据的个数

        }

        for (int pos = 1; pos <= d; pos++) {
            for (int i = 0; i < a.length; i++) {
// 分配过程

                int row = getNumInPos(a[i], pos);

                int col = ++array[row][0];

                array[row][col] = a[i];

            }

            for (int row = 0, i = 0; row < 10; row++) {
// 收集过程

                for (int col = 1; col <= array[row][0]; col++) {
                    a[i++] = array[row][col];

                }

                array[row][0] = 0;

// 复位，下一个pos时还需使用

            }

        }

    }

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 197, 76, 213, 27, 50 };

        radixSort(a, getMaxWeishu(a));

        for (int i : a)

            System.out.print(i + " ");

    }

}