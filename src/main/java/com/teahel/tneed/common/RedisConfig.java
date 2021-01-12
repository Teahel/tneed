package com.teahel.tneed.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**redis配置  使用 Lettuce
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-01-12
 */

@Configuration
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration("182.254.140.133", 6379)
        );

    }
}
