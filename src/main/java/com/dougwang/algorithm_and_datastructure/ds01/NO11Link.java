package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/17 11:12 PM
 * @Description: 原文链接：https://blog.csdn.net/WZL995/article/details/84677141
 * 判断两个链表是否相交，弱相交，求交点（假设链表不带环）
 */
public class NO11Link {

    public static void main(String[] args) {
        ListNode n1 = new ListNode();
        ListNode n2 = new ListNode();
        ListNode n3 = new ListNode();
        ListNode n4 = new ListNode();
        n1.val = 2;
        n2.val = 2;
        n3.val = 3;
        n4.val = 4;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        ListNode n5 = new ListNode();
        ListNode n6 = new ListNode();
        ListNode n7 = new ListNode();
        ListNode n8 = new ListNode();
        n5.val = 1;
        n6.val = 2;
        n7.val = 7;
        n8.val = 8;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        IsPoint(n1, n5);
    }


    public static boolean IsPoint(ListNode first, ListNode head) {

        ListNode forward = first;
        ListNode backward = head;
        if ((first == null) || (head == null)) {
            return false;
        }
        while (first != null) {
            first = first.next;
        }
        while (head != null) {
            head = head.next;
        }
        return first == head;
    }


    public static int Length(ListNode list) {  //求两个链表的长度
        int count = 0;
        while (list != null) {
            list = list.next;
            count++;
        }
        return count;

    }

    public static ListNode Point(ListNode list1, ListNode list2) {
        int len1 = Length(list1);
        int len2 = Length(list2);
        ListNode longL = list1;
        ListNode shortL = list2;
        int diff = len1 - len2;
        if (len2 > len1) {
            longL = list2;
            shortL = list1;
            diff = len2 - len1;
        }                                                //长的链表定为longL遍历    短的shortL遍历
        while (diff-- != 0) {
            longL = longL.next;
        }
        while (true) {
            if (longL == shortL) {
                return longL;
            }
            longL = longL.next;
            shortL = shortL.next;
        }
    }

}
