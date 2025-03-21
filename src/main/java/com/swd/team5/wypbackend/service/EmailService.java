package com.swd.team5.wypbackend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Value("${spring.mail.username}")
    private String email;
    @Autowired
    private JavaMailSender javaMailSender;

    public void SendEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
        log.info(message.toString());
    }

    public void sendPasswordResetEmail(String to, String token) {
        String subject = "Password Reset Request - DIYShoes";
        String body = String.format("""
            Hello,
            
            Click here to reset your password:
            
            http://localhost:5173/reset/%s/%s
            
            If you didn't request a password reset, please ignore this email.
            This token will expire in 30 minutes.
            
            Best regards,
            DIYShoes
            """, token, to);

        SendEmail(to, subject, body);
    }
}
