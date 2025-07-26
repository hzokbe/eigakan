package com.hzokbe.eigakan.service.user;

import com.hzokbe.eigakan.service.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.exception.user.AlreadyRegisteredUserException;
import com.hzokbe.eigakan.exception.user.InvalidPasswordException;
import com.hzokbe.eigakan.exception.user.InvalidUsernameException;
import com.hzokbe.eigakan.model.user.User;
import com.hzokbe.eigakan.model.user.request.UserRequest;
import com.hzokbe.eigakan.model.user.response.UserResponse;
import com.hzokbe.eigakan.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    public UserService(UserRepository repository, PasswordEncoder encoder, JwtService jwtService) {
        this.repository = repository;

        this.encoder = encoder;

        this.jwtService = jwtService;
    }

    public UserResponse signUp(UserRequest request) {
        var username = request.getUsername();

        if (username == null) {
            throw new InvalidUsernameException("username cannot be null");
        }

        username = username.trim();

        if (username.isBlank()) {
            throw new InvalidUsernameException("username cannot be blank");
        }

        if (!username.matches("^[A-Za-z0-9_]+$")) {
            throw new InvalidUsernameException("uername must contain only letters, numbers, and underscores");
        }

        if (repository.existsByUsername(username)) {
            throw new AlreadyRegisteredUserException("already registered user");
        }

        var rawPassword = request.getRawPassword();

        if (rawPassword == null) {
            throw new InvalidPasswordException("password cannot be null");
        }

        if (rawPassword.isBlank()) {
            throw new InvalidPasswordException("password cannot be blank");
        }

        if (rawPassword.length() < 8) {
            throw new InvalidPasswordException("password must be at least 8 characters long");
        }

        var user = new User(username, encoder.encode(rawPassword));

        user = repository.save(user);

        return new UserResponse(user.getId(), username);
    }
}
