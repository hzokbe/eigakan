package com.hzokbe.eigakan.service.user.custom;

import com.hzokbe.eigakan.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
}
