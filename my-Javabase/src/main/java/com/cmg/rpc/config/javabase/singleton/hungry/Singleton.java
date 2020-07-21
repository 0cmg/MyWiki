package com.cmg.rpc.config.javabase.singleton.hungry;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/6/28 21:32
 * @Description
 **/
public class Singleton {
    /**
     * 懒汉式：
     *  延迟创建这个对象
     *
     * (1) 构造器私有化
     * (2) 用一个静态变量保存这个唯一的实例
     * (3) 提供一个静态方法，获取这个实例
     * (4) 线程不安全
     * 适用于单线程
     *
     **/
    private static Singleton instance;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if (instance == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton();
        }
        return instance;
    }
}
