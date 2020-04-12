package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/16 10:27 PM
 * @Description:
 * https://yq.aliyun.com/articles/315766
 * 单链表逆序
 */
public class NO3Link {

    public static LinkNode reverse(LinkNode head){
        LinkNode pre = null;
        LinkNode post = null;
        while (head!=null){
            post = head.next;
            head.next =pre;
            pre = head;
            head = post;
        }
        return pre;
    }
}
