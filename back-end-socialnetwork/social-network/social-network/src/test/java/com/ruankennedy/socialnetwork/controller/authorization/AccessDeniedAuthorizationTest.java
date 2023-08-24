package com.ruankennedy.socialnetwork.controller.authorization;


import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.controller.ClassTestParent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest 
@AutoConfigureMockMvc 
public class AccessDeniedAuthorizationTest extends ClassTestParent {


    @Test
    void accessMyProfileDeniedNoUserLogged() throws Exception {

        mockMvc.perform(get("/userarea/myprofile")) 
                .andExpect(status().is(unauthorized));
    }

    @Test
    void accessChangePasswordDeniedNoUserLogged() throws Exception {
    	
        mockMvc.perform(put("/userarea/changepassword"))
                .andExpect(status().is(unauthorized));

    }

    @Test
    void accessFindAllDeniedNoUserLogged() throws Exception { 

        mockMvc.perform(get("/users"))
                .andExpect(status().is(unauthorized));

    }

    @Test
    void accessFindAllDeniedRoleUserLogged() throws Exception {

        Login loginData = new Login("user1@hotmail.com", "123456");

        String token = authenticate(loginData); 

        mockMvc.perform(get("/users")
                        .header("Authorization", token))
                .andExpect(status().is(forbidden));

    }

    @Test
    void accessFindByIdDeniedNoUserLogged() throws Exception {


        mockMvc.perform(get("/users/{id}", "teste")) 
                .andExpect(status().is(unauthorized)); 
    }

    @Test
    void accessFindByIdDeniedRoleUserLogged() throws Exception {

        Login loginData = new Login("user1@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get("/users/{id}", "teste") 
                        .header("Authorization", token))
                .andExpect(status().is(forbidden));

    }

    @Test
    void accessAnyOtherResourceDeniedNoUserLogged() throws Exception { 

        mockMvc.perform(get("/recursoinvalido"))
                .andExpect(status().is(unauthorized));

    }

}

