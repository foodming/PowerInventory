package com.power.inventory.inventory.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60)
public class RedisHttpSessionConfig extends RedisConfig {

    @Autowired
    Environment env;


    /**
     * 配置redis连接工厂
     *
     * @return */

    @Primary
    @Bean
    public RedisConnectionFactory  defaultRedisConnectionFactory()
    {
        redisConnectionInfo.host = env.getProperty("redis.session.host");
        redisConnectionInfo.port = Integer.parseInt(env.getProperty("redis.session.port"));
        redisConnectionInfo.database = Integer.parseInt(env.getProperty("redis.session.database"));
        redisConnectionInfo.timeout = Integer.parseInt(env.getProperty("redis.session.timeout"));
        redisConnectionInfo.password = env.getProperty("redis.session.password");
        redisConnectionInfo.maxTotal = Integer.parseInt(env.getProperty("redis.session.pool.maxTotal"));
        redisConnectionInfo.maxIdle = Integer.parseInt(env.getProperty("redis.session.pool.maxIdle"));
        redisConnectionInfo.maxWaitMillis = Long.parseLong(env.getProperty("redis.session.pool.maxWaitMillis"));
        redisConnectionInfo.testOnBorrow = Boolean.parseBoolean(env.getProperty("redis.session.pool.testOnBorrow"));


        return createJedisConnectionFactory();
    }

}