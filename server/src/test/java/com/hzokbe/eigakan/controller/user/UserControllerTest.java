package com.hzokbe.eigakan.controller.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldNotAuthorize_whenRequestWithoutAuthorizationHeader() throws Exception {
        var id = UUID.randomUUID().toString();

        mockMvc.perform(get("/users/" + id)).andExpect(status().isUnauthorized());
    }
}
