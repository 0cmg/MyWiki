package com.cmg.rpc.config.rabbitmq.simple;


import com.cmg.rpc.config.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/**
 * @Author cmg
 * @Date 20200616 18：08：59
 * @Describe TODO
 */
public class Recv {
    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception{
        //获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中建立通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
        //获取消息
        while (true){
            Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received '" + message  + "'");
        }
    }
}
