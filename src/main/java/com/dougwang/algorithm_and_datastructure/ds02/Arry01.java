package com.dougwang.algorithm_and_datastructure.ds02;

/**
 * @Author: MikeWang
 * @Date: 2020/3/23 8:35 PM
 * @Description:
 * 数组的查找最大值、最小值、给定值、重复值
 */
public class Arry01 {
    public static void main(String[] args){
        int a[]={12,4,53,51,2,45,13,4,57,21,31,57};
        int sum=0;
        int Max=a[0],Min=a[0];
        for(int i=0;i<a.length;i++){
            sum+=a[i];
        }
        System.out.println("数组元素的和为"+sum);
        Maximun();
    }

    public static void Maximun(){
        int a[]={12,4,53,51,2,45,13,4,57,21,31,57};
        int Max=a[0],Min=a[0];
        for(int i=1;i<a.length;i++){
            if(Max<a[i]){
                Max=a[i];
            }
            if(Min>a[i]){
                Min=a[i];
            }
        }
        System.out.println("数组元素的最大值为"+Max);
        System.out.println("数组元素的最小值为"+Min);

    }

    //也可以用map
    public static void findDupicateInArray(int[] a) {
        int count=0;
        for(int j=0;j<a.length;j++) {
            for(int k =j+1;k<a.length;k++) {
                if(a[j]==a[k]) {
                    count++;
                }
            }
            if(count==1)
                System.out.println( "重复元素 : " +  a[j] );
            count = 0;
        }
    }


}
