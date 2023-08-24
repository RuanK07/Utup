package com.ruankennedy.socialnetwork.controller.userController;


import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.InvalidParamException;
import com.ruankennedy.socialnetwork.service.customException.ResourceNotFoundException;
import com.ruankennedy.socialnetwork.controller.ClassTestParent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FindUsersTest extends ClassTestParent {
	
    private final String path = "/users";

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUsersInvalidParameter() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456"); 

        String token = authenticate(loginData);

        String param = "INVALIDO"; // paramêtro

        mockMvc.perform(get(path + "?role={param}", param) 
                        .header("Authorization", token))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidParamException)) 
                .andExpect(result -> assertEquals("This parameter (role) : { " + param + " } is invalid" 
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void findUsersParameterUser() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        String param = "USER";

        mockMvc.perform(get(path + "?role={param}", param)
                        .header("Authorization", token))
                .andExpect(status().is(ok));

    }

    @Test
    void findUsersParameterAdministrator() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        String param = "ADMIN";

        mockMvc.perform(get(path + "?role={param}", param)
                        .header("Authorization", token))
                .andExpect(status().is(ok));

    }

    @Test
    void findUsersNoParameter() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get(path)
                        .header("Authorization", token))
                .andExpect(status().is(ok));

    }

    @Test
    void findUserByIdNotFound() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        String id = "2";

        mockMvc.perform(get(path + "/{id}", id)
                        .header("Authorization", token))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The user id: " + id + " wasn't found on database"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void findUserByIdSuccess() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        User user = userRepository.findByEmail("user1@hotmail.com").orElseThrow(() ->
                new ResourceNotFoundException("User wasn't found on database"));

        String id = user.getId();

        mockMvc.perform(get(path + "/{id}", id)
                        .header("Authorization", token))
                .andExpect(status().is(ok));

    }

}
