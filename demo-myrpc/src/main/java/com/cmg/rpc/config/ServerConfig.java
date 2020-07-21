package com.cmg.rpc.config;

/**
 * @Name MyWiKi com.cmg.rpc.config
 * @Author cmg
 * @Date 2020/7/21 17:20
 * @Desciption TODO
 **/
public class ServerConfig {

    protected String host;  //注册中心地址
    protected int port;     //注册中心端口

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
