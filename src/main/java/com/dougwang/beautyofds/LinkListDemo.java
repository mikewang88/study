package com.dougwang.beautyofds;

import java.util.LinkedHashMap;

/**
 * @Author: MikeWang
 * @Date: 2020/5/7 2:45 PM
 * @Description:
 * 肯定用过 LinkedHashMap 这个容器。如果你深入研究 LinkedHashMap 的实现原理，就会发现其中就用到了双向链表这种数据结构。
 */
public class LinkListDemo {
    public static void main(String[] args) {
        LinkedHashMap linkedHashMap =  new LinkedHashMap();
        System.out.println(System.nanoTime());
    }
}
