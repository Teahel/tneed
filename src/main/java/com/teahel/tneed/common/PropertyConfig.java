package com.teahel.tneed.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-02-04
 */
@Data
@Configuration
public class PropertyConfig {

    @Value("${file.path}")
    private String imageAddress;
}
