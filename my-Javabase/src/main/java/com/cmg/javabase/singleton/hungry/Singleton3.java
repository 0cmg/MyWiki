package com.cmg.javabase.singleton.hungry;

import java.io.IOException;
import java.util.Properties;

/**
 * @Name MyWiKi
 * @Author cmg
 * @date 2020/6/30 21:14
 * @Description TODO
 **/

/**
 * 在内部类被加载和初始化时，才会创建INSTANCE实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是单独去加载和初始化的
 * 因为是在内部类加载和初始化时，创建的，因此是线程安全的
 * 适用于多线程
 */
public class Singleton3 {
    private Singleton3() {

    }
    private static class Inner{
        private static final Singleton3 INSTANCE  = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return Inner.INSTANCE;
    }
}
