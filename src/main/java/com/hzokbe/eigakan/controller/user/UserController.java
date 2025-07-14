package com.hzokbe.eigakan.controller.user;

import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.eigakan.service.user.UserService;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
