package com.cmg.rabbitmq.route;

import com.cmg.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName Recv
 * @Author cmg
 * @Date 2020/6/17 18:34
 * @Description TODO
 **/
public class Recv {
    private final static String QUEUE_NAME = "test_queue_direct_1";
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception{
        //获取到连接以及MQ通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        //消息内容
        String message = "删除商品";
        channel.basicPublish(EXCHANGE_NAME,"delete",null,message.getBytes());
        System.out.println("[x] Sent'" + message + "'");

        channel.close();
        connection.close();
    }
}
