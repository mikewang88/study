package com.jvm;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @Author: MikeWang
 * @Date: 2020/6/24 11:12 AM
 * @Description:
 */
public class WeakHashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        myHashMap();
        myWeakHashMap();
    }

    public static void myHashMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        String key = new String("k1");
        String value = "v1";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.gc();

        System.out.println(map);
    }

    public static void myWeakHashMap() throws InterruptedException {
        WeakHashMap<String, String> map = new WeakHashMap<String, String>();
        //String key = "weak";
        // 刚开始写成了上边的代码
        //思考一下，写成上边那样会怎么样？ 那可不是引用了
        String key = new String("weak");
        String value = "map";
        map.put(key, value);
        System.out.println(map);
        //去掉强引用
        key = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println(map);
    }
}

