package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = getJedisPoolConfig();
        // JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setUsePool(true);
        connectionFactory.setTimeout(10000);
        return connectionFactory;
    }

    private JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        poolConfig.setMaxTotal(100);
        poolConfig.setMaxIdle(80);
        poolConfig.setMinIdle(2);
        poolConfig.setMaxWaitMillis(10000);
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setTimeBetweenEvictionRunsMillis(1000);
        poolConfig.setMinEvictableIdleTimeMillis(1000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);

        // poolConfig.setMaxTotal(200);
        // poolConfig.setMaxIdle(200);
        // poolConfig.setMinIdle(16);
        // poolConfig.setMaxWaitMillis(10000);
        // poolConfig.setBlockWhenExhausted(true);
        // poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        // poolConfig.setMinEvictableIdleTimeMillis(20000);
        // poolConfig.setTestOnBorrow(true);
        // poolConfig.setTestOnCreate(true);
        // poolConfig.setTestOnReturn(true);
        // poolConfig.setTestWhileIdle(true);

        return poolConfig;
    }

    @Bean
    public CommandLineRunner demo(StudentRepository repository) {
        return (args) -> {
            Student student = new Student("Eng2015001", "John Doe", Student.Gender.MALE, 1);
            
            for (int i = 0; i < 20; i++) {
                repository.save(student);
            }

            while(true){}
        };
    }
}


//     @Bean
//     public RedisTemplate<Object, Object> redisTemplate() {
//         RedisTemplate<Object, Object> template = new RedisTemplate<>();
//         template.setConnectionFactory(jedisConnectionFactory());
//         template.setEnableTransactionSupport(true);
//         template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//         template.setKeySerializer(new StringRedisSerializer());
//         template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
//         template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//         return template;
//     }
// }