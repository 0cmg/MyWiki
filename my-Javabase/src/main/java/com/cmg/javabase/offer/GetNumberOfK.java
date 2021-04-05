package com.cmg.javabase.offer;

import java.util.Arrays;

public class GetNumberOfK {
    public static void main ( String[] args ) {
        int []array = {3,3,3,3,5};
        System.out.println(GetNumberOfK_2(array , 3));
    }
    public static int GetNumberOfK_2(int [] array , int k) {
        if(array == null || array.length <=0 || k < array[0] || k > array[array.length-1]){
            return 0;
        }
        int cnt =0;
        for(int i : array){
            if(i == k){
                cnt++;
            }
        }
        return cnt;
    }
}
