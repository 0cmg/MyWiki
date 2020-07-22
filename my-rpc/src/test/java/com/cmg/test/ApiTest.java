package com.cmg.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Name MyWiKi com.cmg.test
 * @Author cmg
 * @Date 2020/7/21 17:24
 * @Desciption TODO
 **/
public class ApiTest {

    public static void main(String[] args) {
        String[] configs = {"itstack-rpc-consumer.xml", "itstack-rpc-provider.xml"};
        new ClassPathXmlApplicationContext(configs);
    }

}