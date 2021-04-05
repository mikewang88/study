package com.leetcode.day;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: MikeWang
 * @Date: 2021/3/19 9:59 AM
 * @Description:
 */
public class L146 {
     class LRUCache extends LinkedHashMap<Integer,Integer>{
         private int capacity;

         public LRUCache(int capacity){
                super(capacity, 0.75F, true);
                this.capacity = capacity;
         }

         public int get(int key){
             return super.getOrDefault(key,-1);
         }

         public void put(int key,int value){
             super.put(key,value);
         }

         @Override
         protected  boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
             return size() > capacity;
         }

     }
}
