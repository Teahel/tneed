package com.teahel.tneed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TneedApplication extends SpringBootServletInitializer {

 	public static void main(String[] args) {
		SpringApplication.run(TneedApplication.class, args);
 	}


	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	 // 注意这里要指向原先用main方法执行的Application启动类
	 return builder.sources(TneedApplication.class);

 	}


}
