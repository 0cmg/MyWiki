package com.cmg.javabase.singleton.hungry;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/6/28 21:32
 * @Description
 **/
public class Singleton2 {
    /**
     * 懒汉式：
     *  延迟创建这个对象
     * (1) 构造器私有化
     * (2) 用一个静态变量保存这个唯一的实例
     * (3) 提供一个静态方法，获取这个实例
     * (4) 线程安全
     * 适用于多线程
     *
     **/
    private static Singleton2 instance;
    private Singleton2(){

    }
    public static Singleton2 getInstance(){
        synchronized (Singleton2.class){
            if (instance == null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Singleton2();
            }
        }
        return instance;
    }

    /**
     * 优化
     * @return
     */
    public static Singleton2 getInstance2(){
        if(instance == null){
            synchronized (Singleton2.class){
                if (instance == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton2();
                }
            }
        }

        return instance;
    }
}
