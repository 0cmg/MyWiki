package com.cmg.rpc.config.redis.springdata.dao;

import com.cmg.rpc.config.redis.springdata.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {
    @Autowired
    RedisTemplate redisTemplate;
    public void set(String key, String value) {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }
    public String get(String key) {
        ValueOperations ops = redisTemplate.opsForValue();
        return ops.get(key).toString();
    }
    public void setuser(User user) {
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(user.getId(), user);
    }
    public User getuser(String id) {
        ValueOperations<String, User> ops = redisTemplate.opsForValue();
        User user = ops.get(id);
        System.out.println(user);
        return user;
    }
}
