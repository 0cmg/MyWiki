package com.cmg.javabase.offer;

public class PrintMinNumber {
    public static void main(String[] args) {

    }

    public String PrintMinNumber(int [] numbers) {
        StringBuilder sb = new StringBuilder();
        if(numbers == null || numbers.length <= 0){
            return sb.toString();
        }
        for(int i : numbers){
            sb.append(i);
        }
        int Sum = Integer.valueOf(sb.toString());
        
        return sb.toString();
    }
}
