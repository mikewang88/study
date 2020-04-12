package com.dougwang.algorithm_and_datastructure.ds08;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Author: MikeWang
 * @Date: 2020/3/25 9:20 PM
 * @Description: 布隆过滤器 位图+hash表构成
 * 一个网站有100亿 url 存在黑名单中，每条url平均64个字节，这个黑名单要怎么存？
 * 若此时随便输入一个url，你如何判断该url 是否命中黑名单。
 * 为什么不能用散列表
100 亿是一个很大的数量级，这里每条 url 平均 64 字节，全部存储的话需要 640G 的内存空间。
又因为使用了散列表这种数据结构，而散列表是会出现散列冲突的。为了让散列表维持较小的装载因子，
避免出现过多的散列冲突，需要使用链表法来处理，这里就要存储链表指针。因此最后的内存空间可能超过 1000G 了。

位图法有一个优势就是空间不随集合内元素个数的增加而增加。

在这里使用布隆过滤器才是最好的解决方案
 *
 */
//链接：https://juejin.im/post/5de1e37c5188256e8e43adfc
 //https://juejin.im/post/5c959ff8e51d45509e2ccf84
public class Blglq {
}
class BloomFilterDemo {
    public static void main(String[] args) {
        int total = 1000000; // 总数量
        BloomFilter<CharSequence> bf =
                BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
        // 初始化 1000000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }
        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }
        System.out.println("已匹配数量 " + count);
    }
}

