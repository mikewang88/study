package com.dougwang.algorithm_and_datastructure.agthm06;


/**
 * @Author: MikeWang
 * @Date: 2020/4/12 6:53 PM
 * @Description:
 * 字符串算法
 * 1.回文字符串
 * 2.字符串分割成子串，子串都是回文字符串
 * 3.在字典中查找某串
 * 4.实现Tire 树
 * 5.各种动态规划
 *
 */
public class Agthm06 {

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    // 是否回文字符串
    public static void main(String[] args) {
         Node head = new Node(1,null);
        Node head21 = new Node(2,head);
        Node head2 = new Node(3,head21);
        Node head3 = new Node(3,head2);
        Node head4 = new Node(2,head3);
        Node head5 = new Node(1,head4);

        Node midnode = findMid(head5);
        Node rightNode = midnode.next;
        //如果是奇数
       // Node leftNode = reverse(midnode,head5).next;
        //如果是偶数
        Node leftNode = reverse(midnode,head5);
        while (rightNode!=null && leftNode!=null){
            if (rightNode.data == leftNode.data){
                System.out.println("r="+rightNode.data);
                System.out.println("l="+leftNode.data);
                rightNode = rightNode.next;
                leftNode = leftNode.next;
            }else {
                System.out.println("不是回文字符串");
                break;
            }
        }


    }

    //反转链表
    public static Node reverse(Node p,Node head){
        Node pre = null;
        Node post = null;
        Node r = head;
        System.out.println("z---" + r.data);
        while (r!=p){
            post = r.next;
            r.next =pre;
            pre = r;
            r = post;
        }
        r.next = pre;
        return r;
    }

    //无头结点的链表翻转
    public Node inverseLinkList(Node p,Node head) {

        Node pre = null;
        Node r = head;
        System.out.println("z---" + r.data);
        Node post = null;
        while (r != p) {
            post = r.next;
            r.next = pre;
            pre = r;
            r = post;
        }

        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;

    }
    //查找链表中间节点
    public static Node findMid(Node p){
        if (p.next == null){
            return null;
        }
        Node slow = p;
        Node fast = p;
        while (fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next == null){
            System.out.println("链表个数为奇数");
        }
        return slow;
    }

}
