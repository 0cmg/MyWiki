package com.cmg.javabase.heap.maxheap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeap {
    public static void main(String[] args) {
        int []arr = new int[]{1,5,6,2,7,8,9,10,40};
        System.out.println(getLeastNumberSolution(arr, arr.length));
    }
    public static ArrayList<Integer> getLeastNumberSolution(int []input, int k){
        ArrayList<Integer> reslut = new ArrayList<Integer>();
        int length = input.length;
        if(k > length || k == 0){
            return reslut;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for(int i=0;i<length;i++){
            if(maxHeap.size() != k){
                maxHeap.offer(input[i]);
            }else if(maxHeap.peek() > input[i]){
                Integer temp = maxHeap.poll();
                temp = null;
                maxHeap.offer(input[i]);
            }
        }
        for (Integer integer : maxHeap){
            System.out.println(integer);
            reslut.add(integer);
        }
        return reslut;
    }


}
