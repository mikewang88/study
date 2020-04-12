package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/17 11:12 PM
 * @Description:
 * 查找链表中的倒数第K个节点，要求只能遍历一次链表
 */
public class NO8Link {
    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        findMidK(n1,1);
    }

    private static void findMidK(ListNode head,int k) {
        ListNode slow = head;
        ListNode fast = head;
        while (k-->0){
            fast=fast.next;
        }
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;

        }
        System.out.println(slow.val);
    }
}
