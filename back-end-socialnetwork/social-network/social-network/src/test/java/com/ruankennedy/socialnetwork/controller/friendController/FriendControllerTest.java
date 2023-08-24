package com.ruankennedy.socialnetwork.controller.friendController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.ruankennedy.socialnetwork.controller.ClassTestParent;
import com.ruankennedy.socialnetwork.dto.request.Login;

@SpringBootTest 
@AutoConfigureMockMvc
public class FriendControllerTest extends ClassTestParent {
    
    private final String path = "/friends";

    @Test
    public void testCreateFriend() throws Exception {
    	
    	Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        String nicknameToSearch = "user2";
        
        mockMvc.perform(post(path + "/create/friend")
        		.header("Authorization", token)
            	.contentType(MediaType.APPLICATION_JSON)
	                .param("nickname", nicknameToSearch))
            .andExpect(status().isCreated());
    }
    
    @Test
    public void testGetFriendsByUserNickname() throws Exception {
    	
    	Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        String nicknameToSearch = "user1";
        
        mockMvc.perform(get(path + "/allfriends")
        		.header("Authorization", token)
        		.param("nickname", nicknameToSearch))
        .andExpect(status().isOk());
    }

}