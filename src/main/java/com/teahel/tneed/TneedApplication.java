package com.teahel.tneed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TneedApplication {



	public static void main(String[] args) {
		SpringApplication.run(TneedApplication.class, args);
	}

	/**
	 extends SpringBootServletInitializer

	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	 // 注意这里要指向原先用main方法执行的Application启动类
	 return builder.sources(TneedApplication.class);
	 }




	 */
}
