package com.ruankennedy.socialnetwork.service.commentService;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ruankennedy.socialnetwork.dto.request.ChangeComment;
import com.ruankennedy.socialnetwork.dto.request.CreateCommentDTO;
import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.CommentRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.ResourceNotFoundException;
import com.ruankennedy.socialnetwork.service.serviceAction.CommentService;
import com.ruankennedy.socialnetwork.service.serviceAction.PostService;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private PostService postService;
    
    
    @Test
    @Transactional
    void testCreateComment() {
    	
    	User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));
        
        List<Post> posts = postService.getPostsByUserNickname("admin");

        String postId = posts.get(0).getId();
        
    	CreateCommentDTO createCommentDTO = new CreateCommentDTO();
    	createCommentDTO.setComment("Test create Comment");
    	createCommentDTO.setCommentedMoment(LocalDateTime.now());
    	
    	Comment createdComment = commentService.createComment(user, postId, createCommentDTO);

    	Assertions.assertNotNull(createdComment);
    	Assertions.assertNotNull(createdComment.getId());
    	Assertions.assertEquals(createCommentDTO.getComment(), createdComment.getComment());
    	Assertions.assertEquals(createCommentDTO.getCommentedMoment(), createdComment.getCommentedMoment());
    	Assertions.assertEquals(user, createdComment.getUser());
    	Assertions.assertEquals(postId, createdComment.getPost().getId());
    }
    
    @Test
    @Transactional
    void testGetCommentsByPostId() {
    	
    	List<Post> posts = postService.getPostsByUserNickname("admin");

        String postId = posts.get(0).getId();
        
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        
        Assertions.assertNotNull(comments);
        Assertions.assertFalse(comments.isEmpty());
        
        for (Comment comment : comments) {
        	Assertions.assertNotNull(comment.getId());
        	Assertions.assertNotNull(comment.getComment());
        	Assertions.assertNotNull(comment.getCommentedMoment());
        	Assertions.assertEquals(postId, comment.getPost().getId());
        }
    	
    }
    
    @Test
    @Transactional
    void testUpdateComment() {
    	
    	List<Post> posts = postService.getPostsByUserNickname("admin");

        String postId = posts.get(0).getId();
        
        List<Comment> comments = commentService.getCommentsByPostId(postId);

        String commentId = comments.get(0).getId();
        
        ChangeComment editComment = new ChangeComment();
        editComment.setComment("Test edit comment");
        
        Comment editedComment = commentService.updateComment(commentId, editComment);
        
        Assertions.assertNotNull(editedComment);
        Assertions.assertEquals(editComment.getComment(), editedComment.getComment());

        Comment reloadedComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Failed to reload the post"));

        Assertions.assertEquals(editComment.getComment(), reloadedComment.getComment());

        
    }
    

}