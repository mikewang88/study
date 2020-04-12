package com.dougwang.algorithm_and_datastructure.ds02;

import java.util.Arrays;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 8:37 PM
 * @Description: 数组的排序
 * 快排、归并排序
 */
public class Arry02 {
}

//普通快排
//https://www.jianshu.com/p/6a02a657abcb
class QuickSort {

    /**
     * 将数组的某一段进行划分，小的在左边，大的在右边
     *
     * @param a
     * @param start
     * @param end
     * @return
     */
    public static int divide(int[] a, int start, int end) {
        //每次都以最右边的元素作为基准值
        int base = a[end];
        //start 一旦等于end，就说明左右两个指针合并到了同一位置，可以结束次轮循环
        while (start < end) {
            while (start < end && a[start] <= base) {
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            }
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                //交换
                swap(a, start, end);
                end--;
            }
            while (start < end && a[end] >= base) {
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            }
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                swap(a, start, end);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }
        }
        //这里返回start或者end皆可，此时的start和end都为基准值所在的位置
        return end;
    }

    private static void swap(int[] a, int start, int end) {
        int temp = a[start];
        a[start] = a[end];
        a[end] = temp;
    }

    // 排序
    public static void quicksort(int[] a, int start, int end) {
        if (start >= end) {
            //如果只有一个元素，就不用再排下去了
            return;
        } else {
            //如果不止一个元素，继续划分两边递归排序下去
            int partition = divide(a, start, end);
            quicksort(a, start, partition - 1);
            quicksort(a, partition + 1, end);
        }
    }

    public static void main(String[] args) {

        int[] a = new int[]{2, 7, 4, 5, 10, 1, 9, 3, 8, 6};
        int[] b = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] c = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] d = new int[]{1, 10, 2, 9, 3, 2, 4, 7, 5, 6};

        quicksort(a, 0, a.length - 1);

        System.out.println("排序后的结果：");
        for (int x : a) {
            System.out.print(x + " ");
        }
    }

}

//快排优化 三数取中法
//https://www.cnblogs.com/chengxiao/p/6262208.html
class QuickSortMajor {

    //交换值
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //处理枢纽值(中间值)
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (right > left) {
            dealPivot(arr, left, right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;
            while (true) {
                while (arr[i] < arr[pivot]) {
                    i++;
                }
                while (j > left && arr[j] > arr[pivot]) {
                    j--;
                }
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(arr, i, right - 1);
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }


}

//归并排序
//https://www.cnblogs.com/chengxiao/p/6194356.html#4516460
class MergeSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);//左边归并排序，使得左子序列有序
            sort(arr, mid + 1, right, temp);//右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左序列指针
        int j = mid + 1;//右序列指针
        int t = 0;//临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while (j <= right) {//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

}
