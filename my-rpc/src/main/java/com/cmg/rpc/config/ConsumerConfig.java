package com.cmg.rpc.config;

/**
 * @Name MyWiKi com.cmg.rpc.config
 * @Author cmg
 * @Date 2020/7/21 17:18
 * @Desciption TODO
 **/
public class ConsumerConfig<T> {
    /**
     * 接口
     */
    private String nozzle;
    /**
     * 别名
     */
    private String alias;

    //动态代理链接
    protected synchronized T refer() {

        System.out.format("消费者信息=> [接口：%s] [别名：%s] \r\n", nozzle, alias);

        return null;
    }
}
