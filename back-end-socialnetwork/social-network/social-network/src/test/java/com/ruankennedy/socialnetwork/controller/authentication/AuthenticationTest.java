package com.ruankennedy.socialnetwork.controller.authentication;


import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.service.customException.DatabaseException;
import com.ruankennedy.socialnetwork.controller.ClassTestParent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest 
@AutoConfigureMockMvc 
public class AuthenticationTest extends ClassTestParent { 

    private final String path = "/auth";

    @Test
    void loginFailPasswordWrong() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "1234567");

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DatabaseException))
                .andExpect(result -> assertEquals("E-mail and / or password is / are wrong!"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void loginFailEmailWrong() throws Exception {

        Login loginData = new Login("adminn@hotmail.com", "123456");

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DatabaseException))
                .andExpect(result -> assertEquals("E-mail and / or password is / are wrong!"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void loginOk() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().is(ok));

    }

}
