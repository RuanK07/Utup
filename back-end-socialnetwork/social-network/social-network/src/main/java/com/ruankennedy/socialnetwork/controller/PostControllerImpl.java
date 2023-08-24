   package com.ruankennedy.socialnetwork.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruankennedy.socialnetwork.dto.request.ChangePostSubtitle;
import com.ruankennedy.socialnetwork.dto.request.CreatePostDTO;
import com.ruankennedy.socialnetwork.dto.response.PostDTO;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.service.serviceAction.PostService;

@RestController
@RequestMapping("/posts")
public class PostControllerImpl implements PostController {

    private final PostService postService;

    public PostControllerImpl(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create/post")
    public ResponseEntity<PostDTO> createPost(CreatePostDTO request, User userLogged) {
    	Post createdPost = postService.createPost(userLogged, request);
    	PostDTO postDTO = new PostDTO(createdPost);
    	return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<List<PostDTO>> getPostsByUserNickname(String nickname, User userLogged) {
        List<Post> posts = postService.getPostsByUserNickname(nickname);
        List<PostDTO> response = posts.stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/subtitle")
    public ResponseEntity<PostDTO> updatePostSubtitle(ChangePostSubtitle request, String postId, User userLogged) {
        Post updatedPost = postService.updatePostSubtitle(postId, request);
        PostDTO response = new PostDTO(updatedPost);
        return ResponseEntity.ok(response);
    }
    
    @Override
    @GetMapping(params = "subtitle")
    public ResponseEntity<List<PostDTO>> getPostsBySubtitle(@RequestParam("subtitle") String subtitle) {
        List<Post> posts = postService.getPostsBySubtitle(subtitle);
        List<PostDTO> response = posts.stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}