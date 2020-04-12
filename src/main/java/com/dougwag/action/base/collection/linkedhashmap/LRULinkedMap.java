package com.dougwag.action.base.collection.linkedhashmap;

import java.util.LinkedHashMap;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
/**
 * @Author: MikeWang
 * @Date: 2020/3/8 9:11 PM
 * @Description:
 */
public class LRULinkedMap <K, V> {

    /**
     * 最大缓存大小
     */
    private int cacheSize;

    private LinkedHashMap<K, V> cacheMap;

    public LRULinkedMap(int cacheSize){
        this.cacheSize = cacheSize;

        cacheMap = new LinkedHashMap(16, 0.75F, true){

            @Override
            protected boolean removeEldestEntry(Entry eldest) {
                if(cacheSize + 1 == cacheMap.size()){
                    return true;
                }else{
                    return false;
                }
            }
        };
    }

    public void put(K key, V value){
        cacheMap.put(key, value);
    }

    public V get(K key){
        return cacheMap.get(key);
    }

    public Collection<Map.Entry<K, V>> getAll(){
        return new ArrayList<Map.Entry<K, V>>(cacheMap.entrySet());
    }

    public static void main(String[] args) {
        LRULinkedMap<String, Integer> map = new LRULinkedMap<>(3);
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        for (Map.Entry<String, Integer> e : map.getAll()){
            System.out.println(e.getKey()+"====>"+e.getValue());
        }
        System.out.println("\n");
        map.put("key4", 4);
        for (Map.Entry<String, Integer> e : map.getAll()){
            System.out.println(e.getKey()+"====>"+e.getValue());
        }

    }

}
