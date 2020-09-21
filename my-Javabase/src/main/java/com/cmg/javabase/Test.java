package com.cmg.javabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name MyWiki com.cmg.javabase
 * @Author cmg
 * @Date 2020/8/31 16:37
 * @Desciption TODO
 **/
public class Test {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>(10);
//        System.out.println(list.size());
//        list.add(1, "1");
//        System.out.println(list.get(0));
        Cal cl = (int a, int b) -> {return a+b;};
        int c = cl.add(1, 2);
        System.out.println(c);


    }
}
interface Cal{
    int add(int a, int b);
}