package com.cmg.redis.springdata.controller;

import com.cmg.redis.springdata.entity.User;
import com.cmg.redis.springdata.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;

    @RequestMapping("/set")
    @ResponseBody
    public void set(String key, String value) {
        helloService.set(key, value);
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(String key) {
        return helloService.get(key);
    }

    @RequestMapping("/setuser")
    @ResponseBody
    public void setUser() {
        User user = new User();
        user.setId("1");
        user.setUsername("深圳");
        user.setPassword("sang");
        helloService.setuser(user);
    }

    @RequestMapping(value = "/getuser",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUser() {
        return helloService.getuser("1");
    }
}
