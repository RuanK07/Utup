package com.ruankennedy.socialnetwork.controller;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruankennedy.socialnetwork.dto.request.ChangeBiography;
import com.ruankennedy.socialnetwork.dto.request.ChangeProfilePhoto;
import com.ruankennedy.socialnetwork.dto.response.ProfileDTO;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.service.serviceAction.ProfileService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
@Primary 
public class ProfileControllerImpl implements ProfileController {

    private final ProfileService profileService;
    
    private final ModelMapper modelMapper;

    @Override
    @GetMapping(value = "/userprofile")
    public ResponseEntity<ProfileDTO> getProfileByUserNickname(String nickname, User userLogged) {
        Profile profile = profileService.getProfileByUserNickname(nickname);
        ProfileDTO response = modelMapper.map(profile, ProfileDTO.class);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @PutMapping("/photo")
    public ResponseEntity<ProfileDTO> updateProfilePhoto(ChangeProfilePhoto request, User userLogged) {
        Profile profile = profileService.updateProfilePhoto(userLogged, request);
        ProfileDTO response = modelMapper.map(profile, ProfileDTO.class);
        return ResponseEntity.ok().body(response);
    }

    @Override
    @PutMapping("/biography")
    public ResponseEntity<ProfileDTO> updateBiography(ChangeBiography request,  User userLogged) {
        Profile profile = profileService.updateBiography(userLogged, request);
        ProfileDTO response = modelMapper.map(profile, ProfileDTO.class);
        return ResponseEntity.ok().body(response);
    }
}