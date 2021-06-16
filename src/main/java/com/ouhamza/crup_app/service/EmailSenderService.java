package com.ouhamza.crup_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/ouhamzalhss"> Lhouceine OUHAMZA </a>
 */
@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

   @Async
    public void sendMail(SimpleMailMessage mailMessage){
        javaMailSender.send(mailMessage);
    }
}
