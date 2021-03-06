package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/17 11:12 PM
 * @Description:
 * 判断单链表是否带环？弱带环，求环的长度？求环的入口 并计算每种算法的时间复杂度，空间复杂度
 * 小灰
 */
public class NO10Link {

    /**
     * 判断是否有环
     *
     * @param head
     * @return
     */
    public static boolean isCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                System.out.println("相遇点：" + p2.val);
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
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                System.out.println("说明有环");
                slow = head;
                while (slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                System.out.println("入环点："+slow.val);
                break;
            }
        }
        return slow;
    }

    /**
     * 获取环长
     *
     * @param first
     * @return
     */
    public static int CircleLength(ListNode first) {
        ListNode slow = first;
        ListNode fast = first;
        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                int len = 1;
                slow = slow.next;
                fast = fast.next.next;
                while (fast != slow) {
                    len++;
                    slow = slow.next;
                    fast = fast.next.next;
                }
                return len;
            }
        }
        return 0;
    }
}
