package com.cmg.javabase.offer;

import java.util.ArrayList;
import java.util.Arrays;

public class GetLeastNumbers {
    public static void main(String[] args) {
        int []array = {4,5,1,6,2,7,3,8};
        System.out.println(GetLeastNumbers_2(array, 8));
    }
    public static ArrayList<Integer> GetLeastNumbers_1(int [] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(input == null){
            return arrayList;
        }
        int len = input.length;
        if(len <= 0 || len < k){
            return arrayList;
        }
        Arrays.sort(input);
        for(int i=0;i<len-1;i++){
            if(i <= k){
                arrayList.add(input[i]);
            }
        }
        return arrayList;
    }
    public static ArrayList<Integer> GetLeastNumbers_2(int [] input, int k) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(input == null){
            return arrayList;
        }
        int len = input.length;
        if(len <= 0 || len < k){
            return arrayList;
        }
        for(int m=0;m<len-1;m++){
            for(int n=0;n<m;n++){
                if(input[m] < input[n]){
                    int temp = input[m];
                    input[m] = input[n];
                    input[n] = temp;
                }
            }
        }
        for(int i=0;i<len;i++){
            if(i <= k){
                arrayList.add(input[i]);
            }
        }
        return arrayList;
    }
}
