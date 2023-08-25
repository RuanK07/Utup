package com.ruankennedy.socialnetwork.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruankennedy.socialnetwork.dto.response.FriendDTO;
import com.ruankennedy.socialnetwork.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "friend", description = "Operations about friends")
public interface FriendController {
    
	@Operation(summary = "Create friend", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Friend created")
    @ApiResponse(responseCode = "400", description = "Friends parameters profileIds not valid")
	ResponseEntity<FriendDTO> createFriend(@AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged, @RequestParam String nickname);
	
	@Operation(summary = "Get a friend list by User nickname", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Friends found")
    @ApiResponse(responseCode = "404", description = "Friends not found")
	ResponseEntity<List<FriendDTO>> getFriendsByUserNickname(@RequestParam String nickname, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
	
}
