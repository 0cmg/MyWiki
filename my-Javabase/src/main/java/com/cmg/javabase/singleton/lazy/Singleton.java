package com.cmg.javabase.singleton.lazy;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/6/28 21:32
 * @Description
 **/
public class Singleton {
    /**
     * 饿汉式：
     * 在类初始化的时候直接创建实例对象，不管是否需要这个对象
     * <p>
     * (1) 构造器私有化
     * (2) 自行创建，并用静态变量保存
     * (3) 向外提供这个实例
     * (4) 强调这是一个单例，我们可以用final修饰
     * 简介直观
     **/
    public static final Singleton INSTANCE = new Singleton();

    private Singleton() {

    }
}
