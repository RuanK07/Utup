package com.ruankennedy.socialnetwork.controller.userAreaController;


import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.controller.ClassTestParent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc 
public class MyProfileTest extends ClassTestParent { 

    private final String path = "/userarea/myprofile";

    @Test
    void myProfileAdmLogged() throws Exception { 

        Login loginData = new Login("admin@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get(path)
                        .header("Authorization", token)) 
                .andExpect(status().is(ok));

    }

    @Test
    void myProfileUserLogged() throws Exception {

        Login loginData = new Login("user2@hotmail.com", "123456");

        String token = authenticate(loginData);

        mockMvc.perform(get(path)
                        .header("Authorization", token))
                .andExpect(status().is(ok));

    }

}
