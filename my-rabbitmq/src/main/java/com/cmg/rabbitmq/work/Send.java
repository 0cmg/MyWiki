package com.cmg.rabbitmq.work;

import com.cmg.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @Author cmg
 * @Date 20200616 18：08：59
 * @Describe TODO
 */
public class Send {
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception{
        //获取到连接及mq通道
        Connection connection = ConnectionUtil.getConnection();
        //从连接中建立通道
        Channel channel = connection.createChannel();
        //声明(创建)队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i=0;i<100;i++){
            //消息内容
            String message = "Hello World! "+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x] Sent '" +message+"'");
            Thread.sleep(i*10);
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
