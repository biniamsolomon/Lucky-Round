package com.bini.Lucky_Round.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "redis-properties")
@Configuration
@Data
public class SpinRedisConfigProperties {


    private String redisMaster;
    private String redisSentinels;
    private String redisPassword;
}
