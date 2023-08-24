package com.ruankennedy.socialnetwork.controller.postController;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.ruankennedy.socialnetwork.controller.ClassTestParent;
import com.ruankennedy.socialnetwork.dto.request.ChangePostSubtitle;
import com.ruankennedy.socialnetwork.dto.request.CreatePostDTO;
import com.ruankennedy.socialnetwork.dto.request.Login;


@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest extends ClassTestParent{
    
    private final String path = "/posts";
    
    @Test
    void testCreatePost() throws Exception {
    	
    	Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        LocalDateTime postedMoment = LocalDateTime.now();
        
        CreatePostDTO createPostDTO = new CreatePostDTO();
        createPostDTO.setSubtitle("Your subtitle");
        byte[] photo1 = "Test photo 1".getBytes();
        byte[] photo2 = "Test photo 2".getBytes();
        createPostDTO.setPostPhotos(Arrays.asList(photo1, photo2));
        createPostDTO.setPostedMoment(postedMoment);

        String requestContent = objectMapper.writeValueAsString(createPostDTO);
        
        mockMvc.perform(post(path + "/create/post")
        		.header("Authorization", token)
        			.contentType(MediaType.APPLICATION_JSON)
        			.content(requestContent))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.subtitle", is(createPostDTO.getSubtitle())))
            .andExpect(jsonPath("$.postPhoto").isNotEmpty())
        	.andExpect(jsonPath("$.postedMoment").isNotEmpty());

    }
    
    @Test
    void testGetPostsByUserNickname() throws Exception {
    	
    	Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        String nickname = "admin";
        
        mockMvc.perform(get(path + "/profile")
                .header("Authorization", token)
                .param("nickname", nickname))
            .andExpect(status().isOk());
    }

    @Test
    void testUpdatePostSubtitle() throws Exception {
    	
    	Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
           
        ChangePostSubtitle editSubtitle = new ChangePostSubtitle();
        editSubtitle.setSubtitle("Test edited subtitle");
        
        int postPosition = 0;
        
        mockMvc.perform(put(path + "/subtitle")
                		.header("Authorization", token)
					        .contentType(MediaType.APPLICATION_JSON)
					        .param("postPosition", String.valueOf(postPosition))
					        .content("{\"subtitle\": \"" + editSubtitle.getSubtitle() + "\"}"))
                .andExpect(status().isOk());
    }
    
    @Test
    void testGetPostsBySubtitle() throws Exception {
        
        Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        String subtitleToSearch = "Subtitle do post 2";

        mockMvc.perform(get(path)
                .header("Authorization", token)
                .param("subtitle", subtitleToSearch)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].subtitle", everyItem(is(subtitleToSearch))));
    }


}