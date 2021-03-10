package com.teahel.tneed.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @author： L.T.J
 * @date： 2021-03-10
 */
@Component
public class EmailUtils {
    @Autowired
    private  JavaMailSender javaMailSender;

    public  void sendEmail(String username,String message) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("teahel@163.com");
        msg.setTo(username);
        msg.setSubject("Tneed为您支持服务！");
        msg.setText(message);
        javaMailSender.send(msg);
    }
}
