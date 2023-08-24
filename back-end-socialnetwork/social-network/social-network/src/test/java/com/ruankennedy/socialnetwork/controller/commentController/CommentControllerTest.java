package com.ruankennedy.socialnetwork.controller.commentController;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.ruankennedy.socialnetwork.controller.ClassTestParent;
import com.ruankennedy.socialnetwork.dto.request.ChangeComment;
import com.ruankennedy.socialnetwork.dto.request.CreateCommentDTO;
import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;

import com.ruankennedy.socialnetwork.service.serviceAction.CommentService;
import com.ruankennedy.socialnetwork.service.serviceAction.PostService;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest extends ClassTestParent {
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private CommentService commentService;
    
    private final String path = "/comments";

	@Test
    void testCreateComment() throws Exception {
    	
		Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        List<Post> posts = postService.getPostsByUserNickname("admin");

        String postId = posts.get(0).getId();
        
        CreateCommentDTO createCommentDTO = new CreateCommentDTO();
    	createCommentDTO.setComment("Test create Comment");
    	createCommentDTO.setCommentedMoment(LocalDateTime.now());
    	
    	String requestContent = objectMapper.writeValueAsString(createCommentDTO);
        
    	mockMvc.perform(post(path + "/create/comment")
                .header("Authorization", token)
                	.contentType(MediaType.APPLICATION_JSON)
                	.content(requestContent)
		                .param("postId", postId))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.comment", is(createCommentDTO.getComment())))
                .andExpect(jsonPath("$.commentedMoment").isNotEmpty());
    }
    
    @Test
    void testGetCommentsByPostId() throws Exception{
    	
    	Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        List<Post> posts = postService.getPostsByUserNickname("admin");

        String postId = posts.get(0).getId();
    	
        mockMvc.perform(get(path + "/post")
        		.header("Authorization", token)
        			.param("postId", postId))
        .andExpect(status().isOk());
    }
    
    @Test
    void testUpdateComment() throws Exception{
    	
    	Login loginData = new Login("admin@hotmail.com", "123456");
        String token = authenticate(loginData);
        
        List<Post> posts = postService.getPostsByUserNickname("admin");

        String postId = posts.get(0).getId();
        
        List<Comment> comments = commentService.getCommentsByPostId(postId);

        String commentId = comments.get(0).getId();
        
        ChangeComment editComment = new ChangeComment();
        editComment.setComment("Test edited comment");

        mockMvc.perform(put(path + "/edit")
                .header("Authorization", token)
                	.contentType(MediaType.APPLICATION_JSON)
                	.content("{\"comment\": \"" + editComment.getComment() + "\"}")
        				.param("commentId", commentId))
            .andExpect(status().isOk());
    }

}