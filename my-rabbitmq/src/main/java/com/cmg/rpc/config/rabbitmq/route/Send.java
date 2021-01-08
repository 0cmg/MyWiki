package com.cmg.rpc.config.rabbitmq.route;

import com.cmg.rpc.config.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName Send
 * @Author cmg
 * @Date 2020/6/17 18:23
 * @Description TODO
 **/
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        //获取到连接以及MQ通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //消息内容
        String message = "新增商品";
        channel.basicPublish(EXCHANGE_NAME, "insert", null, message.getBytes());
        System.out.println("[x] Sent'" + message + "'");

        channel.close();
        connection.close();
    }
}
