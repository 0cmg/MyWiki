package com.cmg.redis.utils;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.243.140.215", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
    }
}
