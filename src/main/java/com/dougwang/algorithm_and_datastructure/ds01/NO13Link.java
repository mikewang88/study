package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/17 11:13 PM
 * @Description:
 * 求两个已排序的单链表中相同的数据
 * https://www.codeleading.com/article/5101229725/;jsessionid=79C97033E20C414AEEA56665A09269C6
 */
public class NO13Link {
    public static void PrintIntersection(ListNode list1,ListNode list2){
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while((cur1 != null) && (cur2 != null)){
            if(cur1.val < cur2.val){
                cur1 = cur1.next;
            }else if(cur1.val > cur2.val){
                cur2 = cur2.next;
            }else{
                System.out.println(cur1.val);
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
        }
    }
    public static void main(String[] args) {
        ListNode n1 = new ListNode();
        ListNode n2 = new ListNode();
        ListNode n3 = new ListNode();
        ListNode n4 = new ListNode();
        n1.val = 1;
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
        n6.val = 3;
        n7.val = 7;
        n8.val = 8;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        PrintIntersection(n1,n5);
    }
}
