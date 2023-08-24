package com.ruankennedy.socialnetwork.controller.userAreaController;


import com.ruankennedy.socialnetwork.dto.request.ChangePassword;
import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.service.customException.DatabaseException;
import com.ruankennedy.socialnetwork.service.customException.SamePasswordException;
import com.ruankennedy.socialnetwork.controller.ClassTestParent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest 
@AutoConfigureMockMvc
public class ChangePasswordTest extends ClassTestParent {

    private final String path = "/userarea/changepassword";

    @Test
    void passwordDoenstMatchDataBasePassword() throws Exception {

        Login loginData = new Login("user2@hotmail.com", "123456"); 

        String token = authenticate(loginData); 

        ChangePassword changePasswordDTO = new ChangePassword("12345678", "13553353553");

        mockMvc.perform(put(path) 
                        .contentType("application/json") 
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(changePasswordDTO))) 
                .andExpect(status().is(badRequest)) 
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DatabaseException))
                .andExpect(result -> assertEquals("The password is not correct (not match)"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void passwordIsEqualTheLastOne() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        ChangePassword changePasswordDTO = new ChangePassword("123456", "123456");

        mockMvc.perform(put(path)
                        .contentType("application/json")
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(changePasswordDTO)))
                .andExpect(status().is(internalServerError))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SamePasswordException))
                .andExpect(result -> assertEquals("Your new password cannot be equal the last one"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void changePasswordSuccessFlow() throws Exception {

        Login firstLogin = new Login("user1@hotmail.com", "123456");

        String token = authenticate(firstLogin);

        String oldPassword = "123456";

        String newPassword = "1234567";

        ChangePassword changePasswordDTO = new ChangePassword(oldPassword, newPassword);

        passwordChanged(changePasswordDTO, token); 

        Login loginDataAttempt1 = new Login("user1@hotmail.com", oldPassword);

        enterSystemFail(loginDataAttempt1); 

        Login loginDataAttempt2 = new Login("user1@hotmail.com", newPassword);

        enterSystemSuccess(loginDataAttempt2); 

    }

    private void passwordChanged(ChangePassword changePasswordDTO, String token) throws Exception {

        mockMvc.perform(put(path)
                        .contentType("application/json")
                        .header("Authorization", token)
                        .content(objectMapper.writeValueAsString(changePasswordDTO)))
                .andExpect(status().is(ok))
                .andExpect(result -> assertEquals("Password changed successfully!",
                        result.getResponse().getContentAsString()));
    }

    private void enterSystemFail(Login loginDataAttempt1) throws Exception { 

        mockMvc.perform(post("/auth")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginDataAttempt1)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DatabaseException))
                .andExpect(result -> assertEquals("E-mail and / or password is / are wrong!"
                        , result.getResolvedException().getMessage()));
    }

    private void enterSystemSuccess(Login loginDataAttempt2) throws Exception {

        mockMvc.perform(post("/auth")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginDataAttempt2)))
                .andExpect(status().is(ok));
    }

}
