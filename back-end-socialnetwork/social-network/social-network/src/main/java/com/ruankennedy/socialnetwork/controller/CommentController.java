package com.ruankennedy.socialnetwork.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruankennedy.socialnetwork.dto.request.ChangeComment;
import com.ruankennedy.socialnetwork.dto.request.CreateCommentDTO;
import com.ruankennedy.socialnetwork.dto.response.CommentDTO;
import com.ruankennedy.socialnetwork.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "comment", description = "Operations about comments")
public interface CommentController {
	
	@Operation(summary = "Create comment", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Comment created")
    @ApiResponse(responseCode = "400", description = "Comment parameters not valid")
    ResponseEntity<CommentDTO> createComment(@RequestParam String postId, @RequestBody CreateCommentDTO request, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
    
    @Operation(summary = "Get a comment list by post ID", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Comments found")
    @ApiResponse(responseCode = "404", description = "Comments not found")
    ResponseEntity<List<CommentDTO>> getCommentsByPostId(@RequestParam String postId, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
    
    @Operation(summary = "Update Comment by its ID", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Comment updated")
    @ApiResponse(responseCode = "404", description = "Comment not found")
    ResponseEntity<CommentDTO> updateComment(@RequestParam String commentId, @RequestBody ChangeComment request, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
    
}