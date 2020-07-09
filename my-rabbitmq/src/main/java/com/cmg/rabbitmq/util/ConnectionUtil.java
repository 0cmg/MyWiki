package com.cmg.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author cmg
 * @Date 20200616 18：08：59
 * @Describe TODO
 */
public class ConnectionUtil {
    /**
     * @Name ConnectionUtil.getConnection
     * @Author cmg
     * @Params
     * @Return {@link Connection}
     * @throws
     * @date 2020/6/28 21:50
     * Description TODO
     **/
    public static Connection getConnection() throws Exception{

        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务器地址
        factory.setHost("192.168.171.134");
        //端口
        factory.setPort(5672);
        //设置账户信息，用户名、密码、vhost
        factory.setVirtualHost("testhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        //通过工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

}
