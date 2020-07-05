package com.cmg.javabase.classinit;


/**
 * @Name MyWiKi com.cmg.javabase.classinit
 * @Author cmg
 * @date 2020/7/4 8:03
 * @Description TODO
 **/

/**
 * 子类的初始化<clinit>:
 *  * (1.)j=method();
 *  * (2.)子类的静态代码块
 * 先初始化父类：（5）（1）
 * 初始化子类：（10）（5）
 *
 * 子类的实例化方法：
 * （1）super()方法 (9)(3)(2)
 * （2）i=test(); (9)
 * (3)子类的非静态代码块 (8)
 * （4）子类的无参构造（最后） (7)
 */
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
