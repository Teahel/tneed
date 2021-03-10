package com.teahel.tneed.common;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-03-10
 * 打war包要去掉
 */

@Configuration
public class FileConfig {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("E:\\");
        return factory.createMultipartConfig();
    }
}
