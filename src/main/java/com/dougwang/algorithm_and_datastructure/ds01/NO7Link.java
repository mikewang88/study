package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/17 11:12 PM
 * @Description:
 * 查找链表中的中间节点，要求只能遍历一次链表
 */
public class NO7Link {
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
        findMid(n1);
    }

    private static void findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null){
            if(fast.next.next != null){
                slow = slow.next;
                fast = fast.next.next;
            } else {
                slow = slow.next;
            }

        }
        System.out.println(slow.val);
    }

}
