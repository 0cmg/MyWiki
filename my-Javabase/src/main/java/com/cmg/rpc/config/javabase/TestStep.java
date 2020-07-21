package com.cmg.rpc.config.javabase;

import org.junit.Test;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/7/6 21:44
 * @Description TODO
 **/
public class TestStep {

    @Test
    public void test(){
        System.out.println(f(6));
        System.out.println(f(6));
    }
    public int f(int n){
        if(n < 1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n == 1 || n == 2){
            return n;
        }
        return f(n-2) + f(n-1);
    }
    public int f2(int n){
        if(n < 1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n == 1 || n == 2){
            return n;
        }
        int one  = 1;
        int two = 2;
        int step = 0;
        for (int i=3;i<=n;i++){
            step = two + one;
            two = one;
            one = step;
        }
        return step;
    }
}
