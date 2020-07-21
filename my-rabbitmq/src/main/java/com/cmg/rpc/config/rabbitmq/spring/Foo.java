package com.cmg.rpc.config.rabbitmq.spring;

/**
 * @ClassName Foo
 * @Author cmg
 * @Date 2020/6/18 9:11
 * @Description 消费者
 **/
public class Foo {
    //具体执行业务的方法
    public void listen(String foo) {
        System.out.println("\n消费者： " + foo + "\n");
    }
}
