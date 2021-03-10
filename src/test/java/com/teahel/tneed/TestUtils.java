package com.teahel.tneed;

import com.teahel.tneed.common.EmailUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.xml.stream.Location;
import java.time.LocalDateTime;

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
}
