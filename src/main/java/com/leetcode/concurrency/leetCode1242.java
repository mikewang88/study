package com.leetcode.concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: MikeWang
 * @Date: 2020/3/24 3:17 PM
 * @Description:
 * https://blog.yuchen.website/articles/LeetCode%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%BB%83%E4%B9%A0%E9%A2%98%E8%A7%A3.html
 */
public class leetCode1242 {
}
interface HtmlParser {
    List<String> getUrls(String url);
}
class Solution {
    private final Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
    private final List<String> result = Collections.synchronizedList(new ArrayList<String>());
    private String HOSTNAME = null;

    public boolean judgeHostname(String url) {
        int idx = url.indexOf('/', 7);
        String hostName = (idx != -1) ? url.substring(0, idx) : url;
        return hostName.equals(HOSTNAME);
    }

    private void initHostName(String url) {
        int idx = url.indexOf('/', 7);
        HOSTNAME = (idx != -1) ? url.substring(0, idx) : url;
    }

    public void getUrl(String startUrl, HtmlParser htmlParser) {
        result.add(startUrl);
        List<String> res = htmlParser.getUrls(startUrl);
        List<Thread> threads = new ArrayList<>();
        for (String url : res) {
            if (judgeHostname(url) && !set.contains(url)) {
                set.add(url);
                threads.add(new Thread(() -> {
                    getUrl(url, htmlParser);
                }));
            }
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        initHostName(startUrl);
        set.add(startUrl);
        getUrl(startUrl, htmlParser);
        return result;
    }
}
//————————————————
//        版权声明：本文为CSDN博主「zhang0peter」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/zhangpeterx/article/details/104274011