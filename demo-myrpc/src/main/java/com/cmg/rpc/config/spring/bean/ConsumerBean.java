package com.cmg.rpc.config.spring.bean;

import com.cmg.rpc.config.ConsumerConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Name MyWiKi com.cmg.rpc.config.spring.bean
 * @Author cmg
 * @Date 2020/7/21 17:18
 * @Desciption TODO
 **/
public class ConsumerBean<T> extends ConsumerConfig<T> implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return refer();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
