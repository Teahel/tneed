package com.teahel.tneed;

import com.teahel.tneed.common.EmailUtils;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2020-12-21
 */

public class TestUtils extends TneedApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailUtils emailUtils;


    @Autowired
    StringEncryptor stringEncryptor;



    @Test
    void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("teahel@163.com");
        msg.setTo("tianjun_plus@163.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");
        javaMailSender.send(msg);
    }

    @Test
    void testsendEmail(){
        emailUtils.sendEmail("tianjun_plus@163.com","测试种");
        System.out.println("测试完成");
    }

    @Test
    void getEncrytor(){
        System.out.println("matthew:"+stringEncryptor.encrypt("matthew"));
        System.out.println("123456:"+stringEncryptor.encrypt("123456"));
        System.out.println("jdbc:mysql://182.254.140.133/demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=true :"+stringEncryptor.encrypt("jdbc:mysql://182.254.140.133/demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=true"));
        System.out.println("driver-class-name:"+stringEncryptor.encrypt("com.mysql.cj.jdbc.Driver"));
        System.out.println("spring.redis.host:"+stringEncryptor.encrypt("182.254.140.133"));
        System.out.println(" teahel@163.com:"+stringEncryptor.encrypt("teahel@163.com"));
        System.out.println("TYOKUAGAOPWIGNPM:"+stringEncryptor.encrypt("TYOKUAGAOPWIGNPM"));

    }
}
