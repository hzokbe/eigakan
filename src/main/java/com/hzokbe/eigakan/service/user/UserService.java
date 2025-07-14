package com.hzokbe.eigakan.service.user;

import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
