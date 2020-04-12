package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/17 11:13 PM
 * @Description:
 * 判断两个链表是否相交，弱相交，求交点（假设链表可能带环）
 * https://www.jianshu.com/p/634c147fe2a9
 */
public class NO12Link {
    /**
     * 判断是否存在环
     * 步骤：设置两个指针同时指向head，其中一个一次前进一个节点（P1），另外一个一次前进两个节点(P2)。
     * p1和p2同时走，如果其中一个遇到null，则说明没有环，如果走了N步之后，二者指向地址相同，那么说明链表存在环。
     * @return
     */
    public static boolean isLoop(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next!=null&&p2.next.next!=null){
            p1 =p1.next;
            p2 =p2.next.next;
            if (p1==p2){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断入环点
     * @param head
     * @return 头节点---》入环点 = 相遇点--》遇环点
     */
    public static ListNode enterCycleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2){
                System.out.println("说明有环");
                p1 = head;
                while (p1!=p2){
                    p1 = p1.next;
                    p2 = p2.next;
                }
                System.out.println("入环点："+p1.val);
                break;
            }
        }
        return p1;
    }


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
        n5.next = n3;

        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);


        n6.next = n7;
        n7.next = n8;
        n8.next = n5;

        ListNode p1enter =  enterCycleNode(n1);
        ListNode p2enter =  enterCycleNode(n6);
        if (p1enter == p2enter){
            System.out.println("入环点相同 "+p2enter.val);
            ListNode cx =  Point(n1,n6,p1enter);
            System.out.println(cx.val);


        }else {
            System.out.println("入环点相不同 交点分别是 两个入环点"+p1enter.val+" "+p2enter.val);
        }

    }

    public static int LengthEnter(ListNode list,ListNode enter) {  //求两个链表到入长点的的长度
        int count = 0;
        while (list != enter) {
            list = list.next;
            count++;
        }
        return count;

    }

    public static ListNode Point(ListNode list1, ListNode list2,ListNode enter) {
        int len1 = LengthEnter(list1,enter);
        int len2 = LengthEnter(list2,enter);
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
