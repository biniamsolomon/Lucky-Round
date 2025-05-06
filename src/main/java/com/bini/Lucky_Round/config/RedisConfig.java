package com.bini.Lucky_Round.config;//package com.mpesa.spin_the_wheel.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.Cache;
//import org.springframework.cache.annotation.CachingConfigurer;
//import org.springframework.cache.interceptor.CacheErrorHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisKeyValueAdapter;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
//public class RedisConfig implements CachingConfigurer {
//
//    private final SpinRedisConfigProperties spinRedisConfigProperties;
//
//    @Value("${redis-properties.redis-time-to-live}")
//    private long redisTimeToLive;
//
////	@Value("${spring.data.redis.timeout}")
////	private Duration redisCommandTimeout;
//
//    @Bean
//    public LettuceConnectionFactory connectionFactory() {
//
//        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration()
//                .master(this.spinRedisConfigProperties.getRedisMaster());
//        List.of(this.spinRedisConfigProperties.getRedisSentinels().split(","))
//                .forEach(sentinel -> redisSentinelConfiguration.sentinel(sentinel.split(":")[0],
//                        Integer.parseInt(sentinel.split(":")[1])));
//        redisSentinelConfiguration.setPassword(RedisPassword.of(this.spinRedisConfigProperties.getRedisPassword()));
//        redisSentinelConfiguration.setSentinelPassword(RedisPassword.of(this.spinRedisConfigProperties.getRedisPassword()));
//        redisSentinelConfiguration.setDatabase(1);
//
//        return new LettuceConnectionFactory(redisSentinelConfiguration,
//                LettuceClientConfiguration.defaultConfiguration());
//
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new JdkSerializationRedisSerializer());
//        template.setValueSerializer(new JdkSerializationRedisSerializer());
//        template.setEnableTransactionSupport(true);
//
//        template.afterPropertiesSet();
//        return template;
//    }
//
//
//    @Override
//    @Bean
//    public RedisCacheManager cacheManager() {
//        return RedisCacheManager.builder(this.connectionFactory()).cacheDefaults(this.cacheConfiguration())
//                .build();
//    }
//
//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .disableCachingNullValues()
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }
//
//    @Override
//    public CacheErrorHandler errorHandler() {
//        return new CacheErrorHandler() {
//
//
//
//
//            ///////
//            @Override
//            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
//                System.out.println("Failure getting from cache: " + cache.getName() + ", exception: " + exception.toString());
//            }
//
//            @Override
//            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
//                System.out.println("Failure putting into cache: " + cache.getName() + ", exception: " + exception.toString());
//            }
//
//            @Override
//            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
//                System.out.println("Failure evicting from cache: " + cache.getName() + ", exception: " + exception.toString());
//            }
//
//            @Override
//            public void handleCacheClearError(RuntimeException exception, Cache cache) {
//                System.out.println("Failure clearing cache: " + cache.getName() + ", exception: " + exception.toString());
//            }
//        };
//    }
//}
