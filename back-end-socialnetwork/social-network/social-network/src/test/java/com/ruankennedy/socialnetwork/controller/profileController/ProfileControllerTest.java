package com.ruankennedy.socialnetwork.controller.profileController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import com.ruankennedy.socialnetwork.controller.ClassTestParent;
import com.ruankennedy.socialnetwork.dto.request.Login;

@SpringBootTest 
@AutoConfigureMockMvc
public class ProfileControllerTest extends ClassTestParent{
    
    private final String path = "/profile";

    @Test
    void testGetProfileByUser() throws Exception {
    	
        Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        mockMvc.perform(get(path + "/userprofile")
                		.header("Authorization", token))
                .andExpect(status().isOk());
    }
    
    @Test
    void testUpdateProfilePhoto() throws Exception {
    	
        Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        byte[] newProfilePhoto = loadProfilePhotoAsByteArray("static/images/setup2.png");
        
        mockMvc.perform(put(path + "/photo")
                		.header("Authorization", token)
        					.contentType(MediaType.APPLICATION_JSON)
        					.content("{\"profilePhoto\": " + Arrays.toString(newProfilePhoto) + "}"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testUpdateBiography() throws Exception {
    	
        Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        String newBiography = "NovaBiografia";
        
        mockMvc.perform(put(path + "/biography")
                		.header("Authorization", token)
					        .contentType(MediaType.APPLICATION_JSON)
					        .content("{\"biography\": \"" + newBiography + "\"}"))
                .andExpect(status().isOk());
    }
    
    private byte[] loadProfilePhotoAsByteArray(String filePath) {
        try {
            Resource resource = new ClassPathResource(filePath);
            return Files.readAllBytes(resource.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}