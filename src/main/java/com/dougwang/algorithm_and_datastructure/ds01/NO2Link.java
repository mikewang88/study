package com.dougwang.algorithm_and_datastructure.ds01;

/**
 * @Author: MikeWang
 * @Date: 2020/3/16 9:38 PM
 * @Description:
 * 约瑟夫环运作如下：
1、一群人围在一起坐成[2]  环状（如：N）
2、从某个编号开始报数（如：K）
3、数到某个数（如：M）的时候，此人出列，下一个人重新报数
4、一直循环，直到所有人出列[3]  ，约瑟夫环结束


 * 单链表实现约瑟夫环
 * 实现约瑟夫环共3步
 * 1.首先将单链表构成环
 * 2.根据约瑟夫环规则，删除节点
 * 3.让循环继续，直到环中只剩下最后一个节点
 */
class LinkNode{
    int data;
    LinkNode next;
    public LinkNode(){

    }
    public LinkNode(int data){
        this.data = data;
    }
}
public class NO2Link {
    public static LinkNode JosephCycle(LinkNode first, int k){
        //第一步：链表构成环
        LinkNode tail = first;
        while (tail.next!=null){
            tail = tail.next;
        }
        tail.next = first;
        //第二步删除
        LinkNode cur = first;
        while (cur.next!=cur){//当链表中的节点只剩下最后一个时，跳出循环
            LinkNode prev = null;
            for (int i=0;i<k-1;i++){
                prev = cur;
                cur=cur.next;
            }//cur 就是要删除的节点
            prev.next = cur.next;
            cur = null;
            cur = prev.next;//让循环继续
        }
        cur.next =null;
        return cur;
    }

    public static void print(LinkNode head){
        while (head!=null){
            System.out.println(head.data);
            head =head.next;
        }
    }

    public static void main(String[] args) {
        LinkNode n1 = new LinkNode(1);
        LinkNode n2 = new LinkNode(2);
        LinkNode n3 = new LinkNode(3);
        LinkNode n4 = new LinkNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        JosephCycle(n1,3);
        print(n1);
    }
}
