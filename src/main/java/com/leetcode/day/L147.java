package com.leetcode.day;

/**
 * @Author: MikeWang
 * @Date: 2021/3/15 4:25 PM
 * @Description:
 */
public class L147 {
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head,ListNode tail){
        if (head == null){
            return head;
        }
        if (head.next == tail){
            head.next = null;
            return head;
        }

        ListNode slow =head,fast=head;
        while (fast!=tail){
            slow = slow.next;
            fast = fast.next;
            if (fast!=tail){
                fast =fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = sortList(head,mid);
        ListNode list2 = sortList(mid,tail);
        ListNode sorted = merge(list1,list2);
        return sorted;
    }

    public ListNode merge(ListNode head1,ListNode head2){
        ListNode dummyhead  = new ListNode(0);
        ListNode tmp = dummyhead,tmp1= head1,tmp2=head2;
        while (tmp1!=null && tmp2!=null){
            if (tmp1.val<=tmp2.val){
                tmp.next = tmp1;
                tmp1 =tmp1.next;
            }else {
                tmp.next = tmp2;
                tmp2 =tmp2.next;
            }
            tmp = tmp.next;
        }
        if (tmp1!=null){
            tmp.next = tmp1;
        }else if (tmp2!=null){
            tmp.next = tmp2;
        }
        return  dummyhead.next;
    }
}
