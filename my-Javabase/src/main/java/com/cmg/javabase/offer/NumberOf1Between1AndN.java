package com.cmg.javabase.offer;

public class NumberOf1Between1AndN {
    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN_1(13));
    }
    public static int NumberOf1Between1AndN_1(int n) {
        int count = 0;
        for(int i=1;i<=n;i++){
            char []ch = String.valueOf(i).toCharArray();
            for(char c : ch){
                if('1' == c){
                    count++;
                }
            }
        }
        return count;
    }
}
