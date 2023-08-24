package com.ruankennedy.socialnetwork.service.postService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ruankennedy.socialnetwork.dto.request.ChangePostSubtitle;
import com.ruankennedy.socialnetwork.dto.request.CreatePostDTO;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.ResourceNotFoundException;
import com.ruankennedy.socialnetwork.service.serviceAction.PostService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@SpringBootTest
public class PostServiceTest {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostService postService;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    void testCreatePost() {
    	
    	User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));
    	
        CreatePostDTO request = new CreatePostDTO();
        request.setSubtitle("Test subtitle");
        byte[] photo1 = "Test photo 1".getBytes();
        byte[] photo2 = "Test photo 2".getBytes();
        request.setPostPhotos(Arrays.asList(photo1, photo2));
        request.setPostedMoment(LocalDateTime.now());

        Post createdPost = postService.createPost(user, request);

        Assertions.assertNotNull(createdPost.getId());
        Assertions.assertEquals(request.getSubtitle(), createdPost.getSubtitle());

        Assertions.assertArrayEquals(photo1, createdPost.getPostPhoto().get(0));
        Assertions.assertArrayEquals(photo2, createdPost.getPostPhoto().get(1));
        
        Assertions.assertEquals(request.getPostedMoment(), createdPost.getPostedMoment());
    }
    
    @Test
    @Transactional
    void testGetPostsByUserNickname() {
    	
    	User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));
    	
        List<Post> posts = postService.getPostsByUserNickname(user.getNickname());
        
        List<String> postSubtitles = new ArrayList<>();
        for (Post post : posts) {
        	postSubtitles.add(post.getSubtitle());
        }

        List<String> expectedSubtitles = Arrays.asList("Subtitle do post 1");
        Assertions.assertEquals(expectedSubtitles, postSubtitles);
        
    }
    
    @Test
    @Transactional
    void testUpdatePostSubtitle() {
    	
    	User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));
    	
        List<Post> posts = postService.getPostsByUserNickname(user.getNickname());

        String postId = posts.get(0).getId();
        
        ChangePostSubtitle newSubtitle = new ChangePostSubtitle();
        newSubtitle.setSubtitle("Update Subtitle test");
        
        Post updatedPost = postService.updatePostSubtitle(postId, newSubtitle);
        
        Assertions.assertNotNull(updatedPost);
        Assertions.assertEquals(newSubtitle.getSubtitle(), updatedPost.getSubtitle());

    }
    
}