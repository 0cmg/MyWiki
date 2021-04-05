package com.cmg.javabase.offer;

public class InversePairs {
    public static void main(String[] args) {
        int []array = {1,2,3,4,5,6,7,0};
        System.out.println(InversePairs(array));
    }

    public static int InversePairs(int []array){
        if(array == null){
            return -1;
        }
        int len = array.length;
        if(len <= 0){
            return -1;
        }
        int count = 0;
        for (int i = 0; i < len-1; ++i) {
            for (int j = i + 1; j < len-2; ++j) {
                if (array[i] > array[j]) {
                    count += 1;
                }
            }
        }
        return count % 1000000007;
    }
}
