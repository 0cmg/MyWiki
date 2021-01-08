package com.cmg.rpc.config.rabbitmq.work;

import com.cmg.rpc.config.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @Author cmg
 * @Date 20200616 18：08：59
 * @Describe TODO
 */
public class Recv2 {
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {
        //获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中建立通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, false, consumer);
        //获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[x] Received '" + message + "'");
            //休眠
            Thread.sleep(1000);
            //返回确认状态，注释掉表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
