package com.ruankennedy.socialnetwork.controller.userController;


import com.ruankennedy.socialnetwork.dto.request.RegisterUserDTO;
import com.ruankennedy.socialnetwork.service.customException.EmailAlreadyRegisteredException;
import com.ruankennedy.socialnetwork.service.customException.NicknameAlreadyRegisteredException;
import com.ruankennedy.socialnetwork.service.customException.PasswordDoesntMatchRegisterUserException;
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
public class RegistrationUserTest extends ClassTestParent {

    private final String path = "/users/register";

    @Test
    void nicknameAlreadyRegistered() throws Exception { 

        RegisterUserDTO registerDTO = new RegisterUserDTO("admin", "joaomaia@gmail.com", "123456", "123456"); 

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NicknameAlreadyRegisteredException))
                .andExpect(result -> assertEquals("This nickname: " + registerDTO.getNickname() + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void emailAlreadyRegistered() throws Exception {

        RegisterUserDTO registerDTO = new RegisterUserDTO("ruank07", "admin@hotmail.com", "123456", "123456");

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EmailAlreadyRegisteredException))
                .andExpect(result -> assertEquals("This e-mail: " + registerDTO.getEmail() + " already exists in the system"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordsDontMatch() throws Exception {

        RegisterUserDTO registerDTO = new RegisterUserDTO("Larissa", "larissa@hotmail.com", "123456", "1234567");

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasswordDoesntMatchRegisterUserException))
                .andExpect(result -> assertEquals("The passwords don't match"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void registeredSuccess() throws Exception {

        RegisterUserDTO registerDTO = new RegisterUserDTO("Davi", "davi@hotmail.com", "123456", "123456");

        mockMvc.perform(post(path)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().is(created))
                .andExpect(result -> assertEquals(registerDTO.getNickname() + ", your account was registered successfully!",
                        result.getResponse().getContentAsString()));

    }

}
