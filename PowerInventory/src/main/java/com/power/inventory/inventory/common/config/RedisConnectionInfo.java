package com.power.inventory.inventory.common.config;

public class RedisConnectionInfo {
    public String host;
    public Integer port;
    public Integer database;
    public String password;
    public Integer timeout;
    public Integer maxTotal;
    public Integer maxIdle;
    public Long maxWaitMillis;
    public Boolean testOnBorrow;
}
