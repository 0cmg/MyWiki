package com.cmg.javabase.atguigu.autoincrea;

public class SelfIncrease {
    public static void main(String[] args) {
        int i = 1;//i=1
        i = i++;//i=2
        int j = i++;//i=3,j=2
        int k = i + ++i * i++;//i=4*4+1+4=21
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
