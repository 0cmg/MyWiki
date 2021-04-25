package com.cmg.javabase.offer;

import java.util.Arrays;
import java.util.HashMap;

public class MoreThanHalfNum {
    public static void main(String[] args) {
        int []vector = {1,2,2,2,2,1,2,2,61,12,24};
        System.out.println(MoreThanHalfNum_2(vector));
    }
    public static int MoreThanHalfNum_1(int []numbers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = numbers.length;
        if(numbers == null || len <= 0){
            return 0;
        }
        int mid = (int) Math.ceil(len/2);
        for(int num : numbers){
            map.put(num, 0);
        }
        for(int num : map.keySet()){
            int count = 0;
            for(int i : numbers){
                if(i == num){
                    count++;
                }
            }
            if(count >= mid){
                return num;
            }
        }
        return 0;
    }
    public static int MoreThanHalfNum_2(int []numbers) {
        if(numbers == null){
            return 0;
        }
        int len = numbers.length;
        if(len <= 0){
            return 0;
        }
        int count = len/2 + 1;
        for(int i : numbers){
            int co = 0;
            for(int j : numbers){
                if(i == j){
                    co++;
                }
            }
            if(co >= count){
                return i;
            }
        }
        return 0;
    }
}
