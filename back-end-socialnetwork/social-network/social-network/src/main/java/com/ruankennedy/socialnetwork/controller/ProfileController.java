package com.ruankennedy.socialnetwork.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile createdProfile = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable String profileId) {
        Profile profile = profileService.getProfileById(profileId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable String profileId, @RequestBody Profile updatedProfile) {
        Profile profile = profileService.updateProfile(profileId, updatedProfile);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String profileId) {
        boolean deleted = profileService.deleteProfile(profileId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{profileId}/posts")
    public ResponseEntity<Void> addPostToProfile(@PathVariable String profileId, @RequestBody Post post) {
        profileService.addPostToProfile(profileId, post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{profileId}/posts")
    public ResponseEntity<Void> removePostFromProfile(@PathVariable String profileId, @RequestBody Post post) {
        profileService.removePostFromProfile(profileId, post);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{profileId}/friends")
    public ResponseEntity<Void> addFriendToProfile(@PathVariable String profileId, @RequestBody Profile friend) {
        profileService.addFriendToProfile(profileId, friend);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{profileId}/friends")
    public ResponseEntity<Void> removeFriendFromProfile(@PathVariable String profileId, @RequestBody Profile friend) {
        profileService.removeFriendFromProfile(profileId, friend);
        return ResponseEntity.noContent().build();
    }
}