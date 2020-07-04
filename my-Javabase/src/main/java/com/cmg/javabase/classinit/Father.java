package com.cmg.javabase.classinit;

/**
 * @Name MyWiKi com.cmg.javabase.classinit
 * @Author cmg
 * @date 2020/7/4 8:03
 * @Description TODO
 **/
public class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }
    Father(){
        System.out.print("(2)");
    }
    {
        System.out.print("(3)");
    }
    public int test(){
        System.out.print("(4)");
        return 1;
    }

    public static int method(){
        System.out.print("(5)");
        return 1;
    }
}
