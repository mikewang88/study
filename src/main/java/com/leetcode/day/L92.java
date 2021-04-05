package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/19 2:57 PM
 * @Description: 反转链表2
 */
public class L92 {
//    public ListNode reverseBetween(ListNode head, int left, int right) {
//        ListNode dummyNode = new ListNode(-1);
//        dummyNode.next = head;
//        ListNode pre = dummyNode;
//        for (int i=0;i<left-1;i++){
//            pre = pre.next;
//        }
//
//        ListNode rightNode = pre;
//        for (int i=0;i<right-left+1;i++){
//            rightNode = rightNode.next;
//        }
//
//        ListNode leftNode = pre.next;
//        ListNode curr = rightNode.next;
//
//        pre.next =null;
//        rightNode.next =null;
//        //反转链表
//        reverseLinkedList(leftNode);
//
//        pre.next = rightNode;
//        leftNode.next = curr;
//
//        return dummyNode.next;
//    }
//
//    private void reverseLinkedList(ListNode head){
//        ListNode pre = null;
//        ListNode post = null;
//        ListNode cur = head;
//        while (cur!=null){
//            post = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = post;
//        }
//    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i=0;i<left-1;i++){
            pre =pre.next;
        }

        ListNode cur = pre.next;
        ListNode next = null;
        for (int i=0;i<right-left+1;i++){
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}
