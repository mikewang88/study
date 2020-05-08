package com.dougwang.algorithm_and_datastructure.ds01;

import java.util.List;

/**
 * @Author: MikeWang
 * @Date: 2020/3/16 10:59 PM
 * @Description: https://www.cnblogs.com/tengdai/p/9279417.html
 * k 个节点为一组进行反转
 */
public class NO4Link {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        //每k个反转链表
        ListNode node = reverseGroup3(n1, 3);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static ListNode reverseGroup3(ListNode head, int k){
        if (head==null || head.next==null ||k<=1){
            return head;
        }
        ListNode currentNode = head;
        while (k-->1){
            currentNode = currentNode.next;
            if (currentNode==null){
                return head;
            }
        }
        ListNode next = currentNode.next;
        reverse(head,currentNode);
        head.next = reverseGroup3(next,k);
        return currentNode;

    }

    //不停地取k个进行翻转，如果不够k个，就直接返回,结束
    public static ListNode reverseGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;
        ListNode currentNode = head;
        //获取k个元素的首尾节点
        for (int count = 1; count < k; count++) {
            currentNode = currentNode.next;
            //不够K个则返回
            if (currentNode == null)
                return head;
        }
        ListNode next = currentNode.next;
        //对局部链表进行反转
        reverse(head, currentNode);
        head.next = reverseGroup(next, k);
        return currentNode;
    }

    //写一个头尾节点反转的局部函数
    public static ListNode reverse(ListNode head, ListNode tail) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = null;
        ListNode post = null;
        while (pre != tail) {
            post = head.next;
            head.next = pre;
            pre = head;
            head = post;
        }
        return pre;
    }


    //不停地取k个进行翻转，如果不够k个，就直接返回,结束
    public static ListNode reverseGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;
        ListNode currentNode = head;
        ListNode temp = null;
        boolean flag = false;
        //获取k个元素的首尾节点
        for (int count = 1; count < k; count++) {
            temp = currentNode;
            currentNode = currentNode.next;
            //不够K个则返回
            if (currentNode == null) {
                //flag = true;
                return head;
            }
            //return head;
        }
        if (flag) {
            currentNode = temp;
        }
        ListNode next = currentNode.next;
        //对局部链表进行反转
        reverse(head, currentNode);
        if (flag){
            return temp;
        }
        head.next = reverseGroup2(next, k);
        return currentNode;
    }

//    //解法1
//    public ListNode reverseKGroup1(ListNode head, int k) {
//        //计算链表长度
//        int count = 0;
//        ListNode cur = head;
//        while (cur != null) {
//            count++;
//            cur = cur.next;
//        }
//        ListNode preHead = new ListNode(0); //建立假节点存出结果
//        preHead.next = head;
//        ListNode pre = preHead;
//        while (count >= k) {
//            ListNode end = head;
//            for (int i = 1; i < k; i++) //计算出每k段子链表的尾节点
//                end = end.next;
//            ListNode nextHead = end.next; //存储下一段子链表的开始
//            end.next = null; //分割链表
//            pre.next = reverseList(head); //反转子链表
//            head.next = nextHead; //把反转后的子链表的尾节点与下一段链表头拼接
//            pre = head; //移动指针到下一段链表头的前驱
//            head = pre.next; //对下一个子链表进行相同操作
//            count -= k;
//        }
//        return preHead.next;
//    }
//    //见206题
//    public ListNode reverseList(ListNode head) {
//        ListNode first = head;
//        ListNode reverseHead = null;
//        while (first != null) {
//            ListNode second = first.next;
//            first.next = reverseHead;
//            reverseHead = first;
//            first = second;
//        }
//        return reverseHead;
//    }
//
//
//    //解法1
//    public ListNode reverseKGroup2(ListNode head, int k) {
//        ListNode cur = head;
//        int count = 0;
//        while (count < k && cur != null) {
//            cur = cur.next;
//            count++;
//        }
//        //分段递归
//        if (count == k) {
//            cur = reverseKGroup2(cur, k); //cur存储反转k各节点之后的链表
//            while (count > 0) { //反转本段链表，相当于以head为指针再一次遍历前k个节点，把从head开始的每一个节点放到cur前，然后head向后移动，cur向前移动，直到k次
//                ListNode nextHead = head.next;
//                head.next = cur;
//                cur = head;
//                head = nextHead;
//                count--;
//            }
//            head = cur; //把head重新放回到表头用以返回结果
//        }
//        return head;
//    }
//
//    //解法3
//    public ListNode reverseKGroup3(ListNode head, int k) {
//        ListNode cur = head;
//        int count = 0;
//        while (count < k && cur != null) {
//            cur = cur.next;
//            count++;
//        }
//        //分段递归
//        if (count == k) {
//            cur = reverseKGroup3(cur, k); //cur存储反转k各节点之后的链表
//            while (count > 0) { //反转本段链表，相当于以head为指针再一次遍历前k个节点，把从head开始的每一个节点放到cur前，然后head向后移动，cur向前移动，直到k次
//                ListNode nextHead = head.next;
//                head.next = cur;
//                cur = head;
//                head = nextHead;
//                count--;
//            }
//            head = cur; //把head重新放回到表头用以返回结果
//        }
//        return head;
//    }
}
