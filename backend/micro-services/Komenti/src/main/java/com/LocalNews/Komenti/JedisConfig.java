package com.LocalNews.Komenti;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class JedisConfig {


    @Bean
    @Profile("native")
    public RedisConnectionFactory redisConnectionFactoryNative() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }

    @Bean
    @Profile("docker")
    public RedisConnectionFactory redisConnectionFactoryDocker() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("redis");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }
}