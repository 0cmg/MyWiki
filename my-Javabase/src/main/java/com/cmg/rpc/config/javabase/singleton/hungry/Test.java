package com.cmg.rpc.config.javabase.singleton.hungry;
import java.util.concurrent.*;

import java.util.concurrent.Callable;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/6/30 21:20
 * @Description TODO
 **/
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Singleton s1 = Singleton.getInstance();
//        Singleton s2 = Singleton.getInstance();
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s1 == s2);

        Callable<Singleton2> c = new Callable<Singleton2> () {
            @Override
            public Singleton2 call() throws Exception {
                return Singleton2.getInstance();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton2> f1 = es.submit(c);
        Future<Singleton2> f2 = es.submit(c);

        Singleton2 s3 = f1.get();
        Singleton2 s4 = f2.get();
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s3 == s4);
        es.shutdown();

    }
}
