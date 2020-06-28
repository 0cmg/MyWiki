package com.cmg.javabase;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/6/28 21:32
 * @Description
 **/
public class Singleton {
    /**
     * 饿汉式：
     *  直接创建实例对象，不管是否需要这个对象
     * 懒汉式：
     *
     *
     * 一、某个类只能有一个实例 构造器私有化
     * 二、它必须自行创建这个实例 含有一个该类的静态变量来保存这个唯一的实例
     * 三、它必须自行向整个系统提供这个实例 对外提供获取该实例对象的方式
     *    （1）直接暴露
     *    （2）用静态变量的get方法获取
     **/
    public static final Singleton INSTANCE = new Singleton();
    private Singleton(){

    }
    public static void main(String[] args) {

    }
}
