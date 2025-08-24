package com.hzokbe.eigakan.service.user;

import com.hzokbe.eigakan.repository.user.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;
}
