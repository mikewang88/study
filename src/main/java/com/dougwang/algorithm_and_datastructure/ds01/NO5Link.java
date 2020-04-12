package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/16 11:04 PM
 * @Description:
 * 返回链表中间（1/2）节点 扩展返回（1/K）节点
 */
public class NO5Link {

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
        ListNode node = getNodeOfK(n1, 2);
        System.out.print(node.val + " ");
//        while (node != null) {
//            System.out.print(node.val + " ");
//            //node = node.next;
//        }
    }

    /**
     * 返回1/k处的节点，例如中间节点为1/2处节点
     * @param head：头结点
     * @param k：K处
     * @return
     */
    public static ListNode getNodeOfK(ListNode head, int k){
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(!isNull(fast, k)){
            int count = k;
            while(count -- > 0){
                fast = fast.next;
            }
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 当前节点走K步之后是否为空
     * @param head：头结点
     * @param k：K处
     * @return
     */
    public static boolean isNull(ListNode head, int k){
        if(head == null){
            return true;
        }
        ListNode cur = head;
        while(k -- > 0){
            if(cur.next != null){
                cur = cur.next;
            }else{
                return true;
            }
        }
        return false;
    }


}
