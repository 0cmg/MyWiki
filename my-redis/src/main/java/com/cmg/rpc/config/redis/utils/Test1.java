package com.cmg.rpc.config.redis.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test1 {
    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(20);
        JedisPool jedisPool = new JedisPool(config, "10.243.140.215", 6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.ping());
    }
}
