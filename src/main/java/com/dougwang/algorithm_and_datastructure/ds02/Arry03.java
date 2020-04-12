package com.dougwang.algorithm_and_datastructure.ds02;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 8:38 PM
 * @Description:
 * 多个数组的排序，合并、求交集、求并集
 */
public class Arry03 {
//    ————————————————
//    版权声明：本文为CSDN博主「叶子在这儿」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/ccccc1997/article/details/81665658
    //归并排序的merge过程 合并数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1;
        int index=m+n-1;
        while(index>=0){
            //前两个判断要放在前面，防止空指针异常
            if(i<0){
                nums1[index--]=nums2[j--];
            }else if(j<0){
                nums1[index--]=nums1[i--];
            }
            else if(nums1[i]>nums2[j] && i>=0){
                nums1[index--]=nums1[i--];
            }else if(nums1[i]<=nums2[j]&& j>=0){
                nums1[index--]=nums2[j--];
            }
        }
    }

//
//————————————————
//    版权声明：本文为CSDN博主「noob_ming」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/qq_24484085/article/details/77987364

    public static void main(String[] args) {
        int a[] ={18,22,6,7,58,4,101};
        int b[] ={101,21,18,6,5,42,102};
        Set<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < b.length; j++) {

                if(a[i]==b[j]){
                    set.add(a[i]);
                }

            }


        }
        System.out.println("交集："+set);
    }




}
