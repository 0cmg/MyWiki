package com.cmg.rpc.config.spring;

import com.cmg.rpc.config.spring.bean.ConsumerBean;
import com.cmg.rpc.config.spring.bean.ProviderBean;
import com.cmg.rpc.config.spring.bean.ServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Name MyWiKi com.cmg.rpc.config.spring
 * @Author cmg
 * @Date 2020/7/21 17:12
 * @Desciption TODO
 **/
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("consumer", new MyBeanDefinitionParser(ConsumerBean.class));
        registerBeanDefinitionParser("provider", new MyBeanDefinitionParser(ProviderBean.class));
        registerBeanDefinitionParser("server", new MyBeanDefinitionParser(ServerBean.class));
    }
}
