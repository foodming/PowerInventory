package com.power.inventory.inventory.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisAuthConfig extends RedisConfig {

    @Autowired
    Environment env;

    /**
     * 配置redis连接工厂
     *
     * @return */

    @Bean
    public RedisConnectionFactory authRedisConnectionFactory()
    {
        redisConnectionInfo.host = env.getProperty("redis.auth.host");
        redisConnectionInfo.port = Integer.parseInt(env.getProperty("redis.auth.port"));
        redisConnectionInfo.database = Integer.parseInt(env.getProperty("redis.auth.database"));
        redisConnectionInfo.timeout = Integer.parseInt(env.getProperty("redis.auth.timeout"));
        redisConnectionInfo.password = env.getProperty("redis.auth.password");
        redisConnectionInfo.maxTotal = Integer.parseInt(env.getProperty("redis.auth.pool.maxTotal"));
        redisConnectionInfo.maxIdle = Integer.parseInt(env.getProperty("redis.auth.pool.maxIdle"));
        redisConnectionInfo.maxWaitMillis = Long.parseLong(env.getProperty("redis.auth.pool.maxWaitMillis"));
        redisConnectionInfo.testOnBorrow = Boolean.parseBoolean(env.getProperty("redis.auth.pool.testOnBorrow"));

        return createJedisConnectionFactory();
    }

    @Bean(name = "authRedisTemplate")
    public RedisTemplate authRedisTemplate(){
        RedisTemplate template = new RedisTemplate();
        RedisConnectionFactory redisConnectionFactory = authRedisConnectionFactory();
        initDomainRedisTemplate(template, redisConnectionFactory);
        return template;
    }



}
