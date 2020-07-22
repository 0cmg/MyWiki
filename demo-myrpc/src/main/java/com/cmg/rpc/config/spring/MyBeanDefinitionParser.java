package com.cmg.rpc.config.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Name MyWiKi com.cmg.rpc.config.spring
 * @Author cmg
 * @Date 2020/7/21 16:55
 * @Desciption TODO
 **/
public class MyBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;
    public MyBeanDefinitionParser(Class<?> beanClass){
        this.beanClass = beanClass;
    }
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String beanName = element.getAttribute("id");
        parserContext.getRegistry().registerBeanDefinition(beanName, beanDefinition);

        for(Method method : beanClass.getMethods()){
            if(!isProperty(method, beanClass)){
                continue;
            }
            String name = method.getName();
            String methodName = name.substring(3, 4).toLowerCase() + name.substring(4);
            String value = element.getAttribute(methodName);
            beanDefinition.getPropertyValues().addPropertyValue(methodName, value);
        }

        return beanDefinition;
    }

    private boolean isProperty(Method method, Class beanClass){
        String methodName = method.getName();
        boolean flag = methodName.length() > 3 && methodName.startsWith("set") && Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1;
        Method getter = null;

        Class<?> type = method.getParameterTypes()[0];
        try {
            getter = beanClass.getMethod("get" + methodName.substring(3));
        } catch (NoSuchMethodException e){

        }

        if (null == getter){
            try{
                getter = beanClass.getMethod("is" + methodName.substring(3));
            } catch (NoSuchMethodException e){

            }
        }
        flag = getter != null && Modifier.isPublic(getter.getModifiers()) && type.equals(getter.getReturnType());
        return flag;

    }
}