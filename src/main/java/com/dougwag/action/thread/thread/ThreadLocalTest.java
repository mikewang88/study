package com.dougwag.action.thread.thread;

import com.fasterxml.jackson.databind.util.LinkedNode;

/**
 * @Author: MikeWang
 * @Date: 2020/9/24 9:42 AM
 * @Description:
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<LinkedNode> tl = new ThreadLocal<>();
        tl.set(new LinkedNode(1,null));
        tl.remove();
    }
}
