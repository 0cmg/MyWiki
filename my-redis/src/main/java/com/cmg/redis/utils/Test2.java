package com.cmg.redis.utils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class Test2 {
    public static void main(String[] args) {
        Set<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
        clusterNodes.add(new HostAndPort("10.243.140.215", 7001));
        clusterNodes.add(new HostAndPort("10.243.140.215", 7002));
        clusterNodes.add(new HostAndPort("10.243.140.215", 7003));
        clusterNodes.add(new HostAndPort("10.243.140.215", 7004));
        clusterNodes.add(new HostAndPort("10.243.140.215", 7005));
        clusterNodes.add(new HostAndPort("10.243.140.215", 7006));
        JedisCluster jc = new JedisCluster(clusterNodes);
        jc.set("address", "深圳");
        String address = jc.get("address");
        System.out.println(address);
    }
}
