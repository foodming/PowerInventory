package com.power.inventory.inventory.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableCaching
public class RedisConfig {

    protected RedisConnectionInfo redisConnectionInfo = new RedisConnectionInfo();


     /**
     * 配置redisTemplate
     * <p>
     * 通过redisConnectionFactory引入redis在配置文件中的连接配置
     */

    public RedisConnectionFactory createJedisConnectionFactory() {

        /***
        连接池设置
         ***/
        //获得默认的连接池构造器
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder configBuilder =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();

        JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();
        configBuilder.poolConfig(jedisPoolConfig);

        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = configBuilder.build();

        /***
         * 单机设置
         */
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisConnectionInfo.host, redisConnectionInfo.port);
        redisStandaloneConfiguration.setDatabase(redisConnectionInfo.database);

        //jedis连接工厂
         return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);

    }


    /**
     * 设置数据存入 redis 的序列化方式
     *
     * @param redisTemplate
     * @param factory
     */
    protected void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
    }

    protected JedisPoolConfig getJedisPoolConfig() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConnectionInfo.maxTotal);
        jedisPoolConfig.setMaxIdle(redisConnectionInfo.maxIdle);
        jedisPoolConfig.setMaxWaitMillis(redisConnectionInfo.maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(redisConnectionInfo.testOnBorrow);


        return jedisPoolConfig;
    }
}

