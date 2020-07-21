package com.cmg.rpc.config.rabbitmq.subscribe;

import com.cmg.rpc.config.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @Author cmg
 * @Date 20200616 18：08：59
 * @Describe TODO
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception{
        //获取到连接及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中建立通道
        Channel channel = connection.createChannel();
        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[x] Send '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
