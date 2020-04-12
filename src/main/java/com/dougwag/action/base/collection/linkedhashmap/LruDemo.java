package com.dougwag.action.base.collection.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: MikeWang
 * @Date: 2020/3/8 9:08 PM
 * @Description:
 */
public class LruDemo {
    public static void main(String[] args) {
        Map<Integer,Integer> map=new LinkedHashMap<>();
        int n=3;//块数
        int count=0;//中断次数
        int lenth=0;//map元素个数
        int[] arr={3,4,3,1,2};
        for(int i=0;i<arr.length;i++){
            if(lenth<n && map.get(arr[i])==null){
                map.put(arr[i], 0);
                lenth++;
                count++;
            } else{
                if(map.get(arr[i])==null){
                    for(Integer one:map.keySet()){

                        map.remove(one);
                        break;
                    }
                    map.put(arr[i], 0);
                    count++;

                }else{
                    map.remove(arr[i]);
                    map.put(arr[i], 0);
                }
            }

        }
        for(Map.Entry<Integer, Integer> one:map.entrySet()){
            System.out.println(one.getKey());

        }
        System.out.println("count:"+count);

    }

}
