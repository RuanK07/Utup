package com.ruankennedy.socialnetwork.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruankennedy.socialnetwork.dto.request.ChangePostSubtitle;
import com.ruankennedy.socialnetwork.dto.request.CreatePostDTO;
import com.ruankennedy.socialnetwork.dto.response.PostDTO;
import com.ruankennedy.socialnetwork.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "post", description = "Operations about posts")
public interface PostController {
	
	@Operation(summary = "Create post", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Post created")
    @ApiResponse(responseCode = "400", description = "Post parameters not valid")
    ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO request, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
	
	@Operation(summary = "Get a post list by profile ID", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Posts found")
    @ApiResponse(responseCode = "404", description = "Posts not found")
    ResponseEntity<List<PostDTO>> getPostsByUserNickname(@RequestParam String nickname, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
    
	@Operation(summary = "Update post subtitle", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Post subtitle updated")
    @ApiResponse(responseCode = "404", description = "Post not found")
    ResponseEntity<PostDTO> updatePostSubtitle(@RequestBody ChangePostSubtitle request, @RequestParam String postId, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
    
    @Operation(summary = "Get a post list by subtitle", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Posts found")
    @ApiResponse(responseCode = "404", description = "Posts not found")
    ResponseEntity<List<PostDTO>> getPostsBySubtitle(String subtitle);
}