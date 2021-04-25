package com.cmg.javabase.offer;

import java.util.ArrayList;

public class PrintList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(11);
        ListNode l2 = new ListNode(12);
        ListNode l3 = new ListNode(13);
        ListNode l4 = new ListNode(14);
        ListNode l5 = new ListNode(15);
        ListNode l6 = new ListNode(16);
        l1.setNext(l2);
        l2.setNext(l3);
        l3.setNext(l4);
        l4.setNext(l5);
        l5.setNext(l6);
        ArrayList<Integer> list = printListFromTailToHead(l1);
        System.out.println(list);
    }
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        if(listNode != null){
            list.add(listNode.val);
            ListNode node = listNode.next;
            while(node != null){
                list.add(node.val);
                node = node.next;
            }
        }
        int size = list.size();
        if(size > 0){
            for(int i=size-1;i>=0; i--){
                list2.add(list.get(i));
            }
        }
        return list2;
    }
}

