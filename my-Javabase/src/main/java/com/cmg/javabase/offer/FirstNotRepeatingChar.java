package com.cmg.javabase.offer;

public class FirstNotRepeatingChar {
    public static void main(String[] args) {
        String str = "google";
        System.out.println(FirstNotRepeatingChar(str));
    }

    public static int FirstNotRepeatingChar(String str){
        if(str == null || "".equals(str)){
            return -1;
        }
        int len = str.length();
        if(len <= 0 || len > 10000){
            return -1;
        }
        for(char c : str.toCharArray()){
            int lastIndex = str.lastIndexOf(c);
            int firstIndex = str.indexOf(c);
            if(lastIndex == firstIndex){
                return firstIndex;
            }
        }
        return -1;
    }
}
