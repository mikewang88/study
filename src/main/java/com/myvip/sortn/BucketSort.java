package com.myvip.sortn;

/**
 * @Author: MikeWang
 * @Date: 2021/5/9 10:49 AM
 * @Description:
 */
public class BucketSort {
    public static void countSort(int[] arr){
        if (arr == null ||arr.length<2){
            return;
        }
        //找到数组中最大的数
        int max = Integer.MIN_VALUE;
        for (int i =0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
        }
        //实例化一组桶
        int[] bucket =  new int[max+1];
        //把数组中的数放到自己的桶中
        for (int i=0;i<arr.length;i++){
            bucket[arr[i]]++;
        }
        //按照桶的顺序把数倒出来
        int i=0;
        for (int j=0;j<bucket.length;j++){
            while (bucket[j]-->0){
                arr[i++]=j;
            }
        }
    }


    //升级版基数排序
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static void radixSortPlus(int[] arr){
        if (arr == null ||arr.length<2){
            return;
        }
        radixSortPlus(arr,0,arr.length-1,maxbits(arr));
    }

    //arr[l..r]排序  ,  digit 数组中最大的位数
    public static void radixSortPlus(int[] arr,int L,int R,int digit){
        final int radix = 10;
        int i=0;
        int j=0;
        //有多少个数就准备多少个辅助空间
        int[] help =  new int[R-L+1];
        for (int d=1;d<=digit;d++){ // 最大多少位，就进出多少次
            // 10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int[] count = new int[radix]; // count[0..9]
            for (i=L;i<=R;i++){
                j = getDigit(arr[i],d);//得到当前位置上的数
                count[j]++;
            }
            //把count 数组变成累加和的形式
            for (i=1;i<radix;i++){
                count[i] = count[i]+count[i-1];
            }
            //从右往左遍历
            for (i=R;i>=L;i--){
                j = getDigit(arr[i],d);
                help[count[j]-1] =arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    public static int getDigit(int x,int d){
        //x /10 的几次方 然后和10 取模，得到该位置上的数
        return ((x/((int)Math.pow(10,d-1)))%10);
    }


    ///标准的基数排序
    public static int findMax(int[] arrays) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arrays.length; i++) {
            max = Math.max(max, arrays[i]);
        }
        return max;
    }

    public static void radixSort(int[] arrays) {

        int max = findMax(arrays);

        //需要遍历的次数由数组最大值的位数来决定
        for (int i = 1; max / i > 0; i = i * 10) {
            int[][] buckets = new int[arrays.length][10];
            //获取每一位数字(个、十、百、千位...分配到桶子里)
            for (int j = 0; j < arrays.length; j++) {
                int num = (arrays[j] / i) % 10;
                //将其放入桶子里
                buckets[j][num] = arrays[j];
            }
            //回收桶子里的元素
            int k = 0;
            //有10个桶子
            for (int j = 0; j < 10; j++) {
                //对每个桶子里的元素进行回收
                for (int l = 0; l < arrays.length ; l++) {
                    //如果桶子里面有元素就回收(数据初始化会为0)
                    if (buckets[l][j] != 0) {
                        arrays[k++] = buckets[l][j];
                    }
                }
            }

        }
    }



    public static void main(String[] args) {
        int[] arrays = {6,  4322, 432, 344, 55 };
        radixSortPlus(arrays);
        System.out.println(arrays);
    }

}
