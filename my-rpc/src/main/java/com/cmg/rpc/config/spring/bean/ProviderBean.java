package com.cmg.rpc.config.spring.bean;

import com.cmg.rpc.config.ProviderConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Name MyWiKi com.cmg
 * @Author cmg
 * @Date 2020/7/21 16:47
 * @Desciption TODO
 **/
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //发布
        doExport();
    }
}
