package com.hzokbe.eigakan.service.user;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountActivationService {
    private final StringRedisTemplate redisTemplate;

    public AccountActivationService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isValidActivationToken(String activationToken) {
        return redisTemplate.hasKey("account-activation::" + activationToken);
    }

    public String findEmailByActivationToken(String recoverToken) {
        return redisTemplate.opsForValue().get("account-activation::" + recoverToken);
    }

    public void deleteActivationToken(String activationToken) {
        redisTemplate.delete("account-activation::" + activationToken);
    }
}
