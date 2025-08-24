package com.hzokbe.eigakan.service.user;

import com.hzokbe.eigakan.exception.user.InvalidUsernameException;
import com.hzokbe.eigakan.model.user.request.UserRequest;
import com.hzokbe.eigakan.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    public void shouldThrowInvalidUsernameException_whenSavingUserWithNullUsername() {
        var request = new UserRequest(null, "bar");

        assertThrows(InvalidUsernameException.class, () -> service.signUp(request));
    }

    @Test
    public void shouldThrowInvalidUsernameException_whenSavingUserWithBlankUsername() {
        var request = new UserRequest("", "bar");

        assertThrows(InvalidUsernameException.class, () -> service.signUp(request));

        request.setUsername(" ");

        assertThrows(InvalidUsernameException.class, () -> service.signUp(request));
    }
}
