package com.ruankennedy.socialnetwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.ruankennedy.socialnetwork.dto.request.ChangeBiography;
import com.ruankennedy.socialnetwork.dto.request.ChangeProfilePhoto;
import com.ruankennedy.socialnetwork.dto.response.ProfileDTO;
import com.ruankennedy.socialnetwork.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "profiles", description = "Operations about profiles")
public interface ProfileController {

    @Operation(summary = "Get a profile by user's id", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Profile found")
    @ApiResponse(responseCode = "404", description = "Profile not found")
    ResponseEntity<ProfileDTO> getProfileByUser(@AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);

    @Operation(summary = "Update profile photo", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Profile photo updated")
    @ApiResponse(responseCode = "404", description = "Profile not found")
    ResponseEntity<ProfileDTO> updateProfilePhoto(@RequestBody ChangeProfilePhoto request, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);

    @Operation(summary = "Update profile biography", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponse(responseCode = "200", description = "Profile biography updated")
    @ApiResponse(responseCode = "404", description = "Profile not found")
    ResponseEntity<ProfileDTO> updateBiography(@RequestBody ChangeBiography request, @AuthenticationPrincipal
    	    @Parameter(hidden = true) User userLogged);
}