package com.hzokbe.eigakan.service.user;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RecoverPasswordService {
    private final StringRedisTemplate redisTemplate;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private final TemplateEngine templateEngine;

    public RecoverPasswordService(StringRedisTemplate redisTemplate, JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.redisTemplate = redisTemplate;

        this.mailSender = mailSender;

        this.templateEngine = templateEngine;
    }

    public void sendRecoverPasswordLink(String email) throws MessagingException {
        var context = new Context();

        var recoverToken = generateAndSaveRecoverToken(email);

        context.setVariable("frontEndPath", "http://localhost:8081");

        context.setVariable("token", recoverToken);

        var htmlContent = templateEngine.process("reset-password", context);

        var mimeMessage = mailSender.createMimeMessage();

        var mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom(from);

        mimeMessageHelper.setTo(email);

        mimeMessageHelper.setSubject("Password Recovery");

        mimeMessageHelper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
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
