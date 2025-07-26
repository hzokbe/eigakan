package com.hzokbe.eigakan.controller.user;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzokbe.eigakan.model.user.request.UserRequest;
import com.hzokbe.eigakan.model.user.response.UserResponse;
import com.hzokbe.eigakan.service.user.UserService;

import java.time.Duration;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(Authentication authentication, HttpServletResponse response) {
        var jwtResponse = service.signIn(authentication);

        var cookie = ResponseCookie.from("jwt", jwtResponse.getJwt())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(1))
                .sameSite("Strict")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("ok");
    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity security) throws Exception {
        return
            security
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                r -> r
                    .requestMatchers(HttpMethod.POST, "/sign-up")
                    .permitAll()
                    .anyRequest()
                    .denyAll()
            )
            .build();
    }
}
