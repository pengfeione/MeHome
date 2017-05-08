package com.mehome.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.springframework.session.data.redis.com.mehome.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by Administrator on 2017-02-10.
 */
//@Configuration
//@EnableRedisHttpSession
public class RedisSessionConfig {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Bean
//    JedisConnectionFactory redisFactory(@Value("${redis.host}") String redisHost,
//                                        @Value("${redis.port}") String redisPort,
//                                        @Value("${redis.password}") String redisPassword,
//                                        @Value("${redis.database}") String redisDatabase
//    ) {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        if (StringUtils.isEmpty(redisDatabase)) {
//            jedisConnectionFactory.setDatabase(0);
//        } else {
//            try {
//                jedisConnectionFactory.setDatabase(Integer.valueOf(redisDatabase));
//            } catch (NumberFormatException e) {
//                jedisConnectionFactory.setDatabase(0);
//            }
//        }
//        if (StringUtils.isEmpty(redisHost)) {
//            jedisConnectionFactory.setHostName("127.0.0.1");
//        } else {
//            jedisConnectionFactory.setHostName(redisHost);
//        }
//        if (StringUtils.isEmpty(redisPort)) {
//            jedisConnectionFactory.setPort(0);
//        } else {
//            try {
//                jedisConnectionFactory.setPort(Integer.valueOf(redisPort));
//            } catch (NumberFormatException e) {
//                jedisConnectionFactory.setPort(6379);
//            }
//        }
//        if (!StringUtils.isEmpty(redisPassword)) {
//            jedisConnectionFactory.setPassword(redisPassword);
//        }
//        jedisConnectionFactory.setPoolConfig(redisPoolConfig());
//        return jedisConnectionFactory;
//    }
//    JedisPoolConfig redisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setTestOnCreate(true);
//        jedisPoolConfig.setMaxTotal(-1);
//        jedisPoolConfig.setMaxIdle(1000);
//        jedisPoolConfig.setMinIdle(5);
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(864000000);
//        jedisPoolConfig.setNumTestsPerEvictionRun(300000);
//        jedisPoolConfig.setTestOnBorrow(true);
//        jedisPoolConfig.setTestOnReturn(false);
//        jedisPoolConfig.setTestWhileIdle(false);
//        jedisPoolConfig.setMaxWaitMillis(3000);
//        return jedisPoolConfig;
//    }
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(@Value("${redis.host}") String redisHost,
//                                                       @Value("${redis.port}") String redisPort,
//                                                       @Value("${redis.password}") String redisPassword,
//                                                       @Value("${redis.database}") String redisDatabase) throws UnknownHostException {
//        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
//        template.setConnectionFactory(redisFactory(redisHost, redisPort, redisPassword, redisDatabase));
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setKeySerializer(new StringRedisSerializer());
//        log.info("-->redisTemplate init");
//        return template;
//    }

}
