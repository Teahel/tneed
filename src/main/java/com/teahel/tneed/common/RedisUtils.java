package com.teahel.tneed.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.net.URL;

/**
 * redis 工具类
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-01-12
 */

public class RedisUtils {
    // inject the actual template
    @Autowired
    private RedisTemplate<String, Object> template;

    public  static void set(String key,Object value){
        template.setStringSerializer();
    }
}
