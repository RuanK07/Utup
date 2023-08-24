package com.ruankennedy.socialnetwork.controller.authorization;


import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.controller.ClassTestParent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccessAllowedAuthorizationTest extends ClassTestParent {

    @Test
    void accessSwagger() throws Exception {

        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(result -> assertEquals(ok, result.getResponse().getStatus()));

    }
    
    @Test
    void accessAuthentication() throws Exception {

        mockMvc.perform(post("/auth"))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));

    }

    @Test
    void accessMyProfileAllowedRoleAdmin() throws Exception { 

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);


        mockMvc.perform(get("/userarea/myprofile")
                        .header("Authorization", token))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));


    }

    @Test
    void accessMyProfileAllowedRoleUser() throws Exception {

        Login loginData = new Login("user1@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get("/userarea/myprofile")
                        .header("Authorization", token))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));

    }

    @Test
    void accessChangePasswordAllowedRoleAdmin() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(put("/userarea/changepassword")
                        .header("Authorization", token))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));


    }

    @Test
    void accessChangePasswordAllowedRoleUser() throws Exception {  

        Login loginData = new Login("user1@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(put("/userarea/changepassword")
                        .header("Authorization", token))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));

    }

    @Test
    void accessRegisterUser() throws Exception { 

        mockMvc.perform(post("/users/register"))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));

    }

    @Test
    void accessFindAllAllowedRoleAdminLogged() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get("/users")
                        .header("Authorization", token))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));

    }

    @Test
    void accessFindByIdAllowedRoleAdminLogged() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get("/users/{id} ", "teste")
                        .header("Authorization", token))
                .andExpect(result -> assertNotEquals(unauthorized, result.getResponse().getStatus()))
                .andExpect(result -> assertNotEquals(forbidden, result.getResponse().getStatus()));

    }

    @Test
    void accessAnyOtherResourcedRoleAdminLogged() throws Exception {

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);


        mockMvc.perform(get("/recursoinvalido")
                        .header("Authorization", token))
                .andExpect(status().is(notFound));

    }

    @Test
    void accessAnyOtherResourceRoleUserLogged() throws Exception { 


        Login loginData = new Login("user1@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get("/recursoinvalido")
                        .header("Authorization", token))
                .andExpect(status().is(notFound));

    }

}

