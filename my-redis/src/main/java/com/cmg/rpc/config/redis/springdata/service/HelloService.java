package com.cmg.rpc.config.redis.springdata.service;

import com.cmg.rpc.config.redis.springdata.dao.HelloDao;
import com.cmg.rpc.config.redis.springdata.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    HelloDao helloDao;

    public void set(String key, String value) {
        helloDao.set(key, value);
    }

    public String get(String key) {
        return helloDao.get(key);
    }

    public void setuser(User user) {
        helloDao.setuser(user);
    }

    public String getuser(String id) {
        String s = helloDao.getuser(id).toString();
        return s;
    }
}
