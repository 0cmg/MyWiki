package com.cmg.javabase.offer;

public class FindFirstCommonNode {
    public static void main ( String[] args ) {

    }

    public static ListNode FindFirstCommonNode ( ListNode pHead1 , ListNode pHead2 ) {
        ListNode node1 = pHead1;
        ListNode node2 = pHead2;
        if(node1 == null || node2 == null){
            return null;
        }
        while(node1 != node2){
            node1 = node1 == null ? pHead1 : node1.next;
            node2 = node2 == null ? pHead2 : node2.next;
        }
        return node1;
    }
}