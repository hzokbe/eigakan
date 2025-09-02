package com.hzokbe.eigakan.service.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RecoverPasswordService {
    private final StringRedisTemplate redisTemplate;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public RecoverPasswordService(StringRedisTemplate redisTemplate, JavaMailSender mailSender) {
        this.redisTemplate = redisTemplate;

        this.mailSender = mailSender;
    }

    public void sendRecoverPasswordLink(String email) {
        var message = new SimpleMailMessage();

        message.setFrom(from);

        message.setTo(email);

        message.setSubject("Password recover token");

        message.setText("Recover token: " + generateAndSaveRecoverToken(email));

        mailSender.send(message);
    }

    public String generateAndSaveRecoverToken(String email) {
        var recoverToken = UUID.randomUUID().toString();

        redisTemplate.opsForValue().set("recover::" + recoverToken, email, 15, TimeUnit.MINUTES);

        return recoverToken;
    }

    public boolean isValidRecoverToken(String recoverToken) {
        return redisTemplate.hasKey("recover::" + recoverToken);
    }

    public String findEmailByRecoverToken(String recoverToken) {
        return redisTemplate.opsForValue().get("recover::" + recoverToken);
    }

    public void deleteRecoverToken(String recoverToken) {
        redisTemplate.delete("recover::" + recoverToken);
    }
}
