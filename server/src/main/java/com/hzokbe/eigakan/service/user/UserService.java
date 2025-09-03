package com.hzokbe.eigakan.service.user;

import com.hzokbe.eigakan.exception.user.*;
import com.hzokbe.eigakan.model.jwt.JwtResponse;
import com.hzokbe.eigakan.model.user.request.ResetPasswordRequest;
import com.hzokbe.eigakan.model.user.request.SendRecoverPasswordLinkRequest;
import com.hzokbe.eigakan.service.jwt.JwtService;
import jakarta.mail.MessagingException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hzokbe.eigakan.model.user.User;
import com.hzokbe.eigakan.model.user.request.UserRequest;
import com.hzokbe.eigakan.model.user.response.UserResponse;
import com.hzokbe.eigakan.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final RecoverPasswordService recoverPasswordService;

    public UserService(UserRepository repository, PasswordEncoder encoder, JwtService jwtService, RecoverPasswordService recoverPasswordService) {
        this.repository = repository;

        this.encoder = encoder;

        this.jwtService = jwtService;

        this.recoverPasswordService = recoverPasswordService;
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

        var email = request.getEmail();

        if (email == null) {
            throw new InvalidPasswordException("email cannot be null");
        }

        if (email.isBlank()) {
            throw new InvalidPasswordException("email cannot be blank");
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new InvalidEmailException("invalid email");
        }

        if (repository.existsByEmail(email)) {
            throw new InvalidEmailException("already registered email");
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

        var user = new User(username, email, encoder.encode(rawPassword));

        user = repository.save(user);

        return new UserResponse(user.getId(), username, email);
    }

    public JwtResponse signIn(Authentication authentication) {
        return jwtService.generateJwt(authentication);
    }

    public void sendRecoverPasswordLink(SendRecoverPasswordLinkRequest request) throws MessagingException {
        var email = request.getEmail();

        if (email == null) {
            throw new InvalidPasswordException("email cannot be null");
        }

        if (email.isBlank()) {
            throw new InvalidPasswordException("email cannot be blank");
        }

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new InvalidEmailException("invalid email");
        }

        if (repository.existsByEmail(email)) {
            recoverPasswordService.sendRecoverPasswordLink(email);
        }
    }

    public void resetPassword(String recoverToken, ResetPasswordRequest request) {
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

        if (!recoverPasswordService.isValidRecoverToken(recoverToken)) {
            throw new InvalidTokenException("invalid or expired token");
        }

        var email = recoverPasswordService.findEmailByRecoverToken(recoverToken);

        if (email == null) {
            return;
        }

        var optionalUser = repository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new ResetPasswordException("unable to reset the password");
        }

        var user = optionalUser.get();

        user.setPasswordHash(encoder.encode(rawPassword));

        repository.save(user);

        recoverPasswordService.deleteRecoverToken(recoverToken);
    }
}
