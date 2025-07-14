package com.hzokbe.eigakan.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;

        this.encoder = encoder;
    }
}
