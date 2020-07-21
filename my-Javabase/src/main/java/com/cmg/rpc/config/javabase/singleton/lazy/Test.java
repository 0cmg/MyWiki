package com.cmg.rpc.config.javabase.singleton.lazy;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/6/30 21:20
 * @Description TODO
 **/
public class Test {
    public static void main(String[] args) {
        Singleton s1 = Singleton.INSTANCE;
        System.out.println(s1);

        Singleton2 s2 = Singleton2.INSTANCE;
        System.out.println(s2);

        Singleton3 s3 = Singleton3.INSTANCE;
        System.out.println(s3);

    }
}
