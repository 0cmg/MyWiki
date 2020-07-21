package com.cmg.rpc.config.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName SpringMain
 * @Author cmg
 * @Date 2020/6/18 9:12
 * @Description TODO
 **/
public class SpringMain {
    public static void main(final String... args) throws Exception {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/rabbitmq-context.xml");
        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        //发送消息
        template.convertAndSend("Hello, 鸟鹏!");
        // 休眠1秒
        Thread.sleep(1000);
        //容器销毁
        ctx.destroy();
    }
}
