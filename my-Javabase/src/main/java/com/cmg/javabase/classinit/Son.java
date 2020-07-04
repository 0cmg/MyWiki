package com.cmg.javabase.classinit;


/**
 * @Name MyWiKi com.cmg.javabase.classinit
 * @Author cmg
 * @date 2020/7/4 8:03
 * @Description TODO
 **/
public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }
    Son(){
        System.out.print("(7)");
    }
    {
        System.out.print("(8)");
    }
    public int test(){
        System.out.print("(9)");
        return 1;
    }

    public static int method(){
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
