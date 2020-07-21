package com.cmg.test.service.impl;

import com.cmg.test.service.HelloService;

/**
 * @Name MyWiKi com.cmg.test.service.impl
 * @Author cmg
 * @Date 2020/7/21 17:25
 * @Desciption TODO
 **/
public class HelloServiceImpl implements HelloService {

    @Override
    public void echo() {
        System.out.println("hi itstack demo rpc");
    }

}
