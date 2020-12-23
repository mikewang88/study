package com.dougwag.action.hashmap;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @Author: MikeWang
 * @Date: 2020/2/26 8:41 PM
 * @Description:
 */
public class HashMapTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        HashMap<String, Integer> hashMap = new HashMap<>(10);
        Class clazz = HashMap.class;
        // threshold是hashmap对象里的一个私有变量，若hashmap的size超过该数值，则扩容。这是通过反射获取该值
        Field field = clazz.getDeclaredField("threshold");
        //setAccessible设置为true可以开启对似有变量的访问
        field.setAccessible(true);
        int threshold_begin = (int) field.get(hashMap);
        System.out.println("初始值："+threshold_begin);
        int threshold = 0;
        for (int i = 0; i < 20; i++) {
            hashMap.put(String.valueOf(i), 0);
            if ((int) field.get(hashMap) != threshold) {
                threshold = (int) field.get(hashMap);
                System.out.println(threshold);
                // 默认的负载因子是0.75,也就是说实际容量是/0.75
                System.out.println((int) field.get(hashMap) / 0.75);
            }
            hashMap.get("2");
        }
    }
}
