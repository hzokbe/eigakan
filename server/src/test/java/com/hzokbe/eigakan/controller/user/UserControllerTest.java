package com.hzokbe.eigakan.controller.user;

import com.hzokbe.eigakan.exception.user.UserNotFoundException;
import com.hzokbe.eigakan.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @Test
    void shouldNotAuthorize_whenRequestWithoutAuthorizationHeader() throws Exception {
        var id = UUID.randomUUID().toString();

        mockMvc.perform(get("/users/" + id)).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "hzokbe")
    void shouldReturnUserNotFound_whenRequestWithAuthorizationHeader() throws Exception {
        var id = UUID.randomUUID().toString();

        doThrow(UserNotFoundException.class).when(service).findById(id);

        mockMvc.perform(get("/users/" + id)).andExpect(status().isBadRequest());
    }
}
