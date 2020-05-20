### 在SpringBoot中读取XXX.properties等配置文件有许多方法
常见的方法有四种：
>+ @ConfigurationProperties方式、
>+ 使用@Value注解方式、
>+ 使用Environment
>+ 使用PropertiesLoaderUtils

本文中主要讲述通过@Value注解方式读取配置文件进行静态变量初始化，从而进行集中式配置文件管理。
一般的，使用@Value注解方式需要在使用的地方进行侵入式编码,即哪里使用需要属性值就要在哪里使用该注解，从SpringCloud配置中心中取值也是如此，一旦变量名发生了变化，则需要进行多处调整，如：

```java
package com.icerno.spring.controller;  
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;  

/**  
*  
* Copyright: Copyright (c) 2018 Jun_Zhou  
*  
* @ClassName: PropertyController.java  
* @Description: 属性文件控制类;  
*  
* @version: v1.0.0  
* @author: JunZhou  
* @Email: 1769676159@qq.com  
* @Site: CERNO  
* @date: 2018年12月19日 下午9:38:28  
*  
*/  
@RestController  
@RequestMapping("/property/")  
public class PropertyController{  
    @Value("${my.name}")  
    public String myName;//侵入式编码,倘若使用较多，则不便更新；  
      
    @RequestMapping("getStaticProperty")  
    public String getStaticProperty() {  
        return myName;  
    }  
}
```  

为了解决侵入式编码带来的不便，建议使用静态变量统一管理配置属性，如：

```java
application.properties  
server.port=9090  
my.name=j......u  
gitlab.gitLabUrl=http://10.10.10.101  
gitlab.serverIP = 10.10.10.101  
gitlab.passWord=z......2017  
gitlab.adminToken=iJ......BV72y`  


`package com.icerno.spring.utils;  
 
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.context.annotation.Configuration;  
  
/** Copyright: Copyright (c) 2018 Jun_Zhou  
*  
* @ClassName: GitlabConfig.java  
* @Description: gitlab相关的配置信息; 
* @version: v1.0.0  
* @author: JunZhou  
* @Email: 1769676159@qq.com  
* @Site: CERNO  
* @date: 2018年11月6日 下午3:32:54 */  
@Configuration  
public class GitlabConfig{  

    public static String		SERVER_IP;//gitlab服务器的Ip;  
    @Value("${gitlab.serverIP}")  
    public  void setSERVER_IP(String sERVER_IP){  
        GitlabConfig.SERVER_IP = sERVER_IP;  
    }  

    public static String GITLAB_URL;//gitlab服务器的URL;  
    @Value("${gitlab.gitLabUrl}")  
    public  void setGITLAB_URL(String gITLAB_URL){  
        GitlabConfig.GITLAB_URL = gITLAB_URL;  
    }  

    public static String		GITLAB_PSW;// gitlab密码;  
    @Value("${gitlab.passWord}")  
    public  void setGITLAB_PSW(String gITLAB_PSW){  
        GitlabConfig.GITLAB_PSW = gITLAB_PSW;  
    }  

    public static String		GITLAB_ADMIN_TOKEN;//gitlab管理员的token信息;  
    @Value("${gitlab.adminToken}")  
    public  void setGITLAB_ADMIN_TOKEN(String gITLAB_ADMIN_TOKEN){  
        GitlabConfig.GITLAB_ADMIN_TOKEN = gITLAB_ADMIN_TOKEN;  
    }  

}
```

在使用@Value注解给静态变量注入属性值的时候需要注意：
>+ 1、为静态变量注入属性值和非静态变量不同，需要提供setXXX方法，该方法有参，且类型和属性类型一致，必须为非静态，在方法体内为静态属性变量赋值；
>+ 2、@Value必须添加在setXXX的方法上;
>+ 3、静态属性所在类必须使用@Component注解或者@Configuration修饰;
>+ 4、setXXX方法的方法名和静态属性变量无必要联系，但是在方法体内必须进行变量的赋值操作。
否则将注入失败，取值为空;
参考代码如下：

```java
package com.icerno.spring.utils;  

import org.springframework.beans.factory.annotation.Value;  
import org.springframework.context.annotation.Configuration;  
 
/** Copyright: Copyright (c) 2018 Jun_Zhou  
*  
* @ClassName: GitlabConfig.java  
* @Description: gitlab相关的配置信息;  
* @version: v1.0.0  
* @author: JunZhou  
* @Email: 1769676159@qq.com  
* @Site: CERNO  
* @date: 2018年11月6日 下午3:32:54 */  
@Configuration  
public class GitlabConfig{  

    public static String		SERVER_IP;//gitlab服务器的Ip;  
    @Value("${gitlab.serverIP}")  
    public  void setSERVER_IP(String sERVER_IP){  
        GitlabConfig.SERVER_IP = sERVER_IP;//方法的方法名和静态属性变量无必要联系，但是在方法体内必须进行变量的赋值操作。  
    }  

    public static String		GITLAB_URL;//gitlab服务器的URL;  
    @Value("${gitlab.gitLabUrl}")  
    public  void setGITLAB_URL(String gITLAB_URL){  
        GitlabConfig.GITLAB_URL = gITLAB_URL;  
    }  

    public static String		GITLAB_PSW;// gitlab密码;  
    @Value("${gitlab.passWord}")  
    public  void setGITLAB_PSW(String gITLAB_PSW){  
        GitlabConfig.GITLAB_PSW = gITLAB_PSW;  
    }  

    public static String		GITLAB_ADMIN_TOKEN;//gitlab管理员的token信息;  
    @Value("${gitlab.adminToken}")  
    public  void setGITLAB_ADMIN_TOKEN(String gITLAB_ADMIN_TOKEN){  
        GitlabConfig.GITLAB_ADMIN_TOKEN = gITLAB_ADMIN_TOKEN;  
    }  
}
```

参考文章:
SpringBoot四种读取properties文件的方式
SpringBoot使用@Value给静态变量注入值
