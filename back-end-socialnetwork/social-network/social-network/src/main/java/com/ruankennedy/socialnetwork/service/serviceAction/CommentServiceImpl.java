package com.ruankennedy.socialnetwork.service.serviceAction;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.ruankennedy.socialnetwork.dto.request.ChangeComment;
import com.ruankennedy.socialnetwork.dto.request.CreateCommentDTO;
import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.CommentRepository;
import com.ruankennedy.socialnetwork.repository.PostRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
    
	private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, PostService postService) {
    	this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @Override
    public Comment createComment(User userLogged, String postId, CreateCommentDTO createCommentDTO) {
    	
    	Optional<User> userOptional = userRepository.findById(userLogged.getId());
    	
    	User user = userOptional.get();

        Optional<Post> postOptional = postRepository.findById(postId);
        
        Post post = postOptional.get();
    	
        Comment comment = Comment.builder()
                .comment(createCommentDTO.getComment())
                .commentedMoment(createCommentDTO.getCommentedMoment())
                .user(user)
                .post(post)
                .build();
         
         commentRepository.save(comment);
         
         user.getComments().add(comment);
         post.getComments().add(comment);;
        
        return comment;

    }

    @Override
    public Comment getCommentById(String commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
    }

    @Override
    public List<Comment> getCommentsByPostId(String postId) {
        Post post = postService.getPostById(postId);
        return commentRepository.findByPost(post);
    }

    @Override
    public Comment updateComment(String commentId, ChangeComment request) {
        Comment comment = getCommentById(commentId);
        comment.setComment(request.getComment());
        return commentRepository.save(comment);
    }
}