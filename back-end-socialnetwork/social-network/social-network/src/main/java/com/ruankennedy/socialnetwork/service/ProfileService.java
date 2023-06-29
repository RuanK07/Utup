package com.ruankennedy.socialnetwork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getProfileById(String profileId) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        return optionalProfile.orElse(null);
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile updateProfile(String profileId, Profile updatedProfile) {
        Optional<Profile> existingProfileOptional = profileRepository.findById(profileId);
        if (existingProfileOptional.isEmpty()) {
            return null;
        }

        Profile existingProfile = existingProfileOptional.get();
        existingProfile.setProfilePhoto(updatedProfile.getProfilePhoto());
        existingProfile.setBiography(updatedProfile.getBiography());

        return profileRepository.save(existingProfile);
    }
    
    public Profile updateProfilePhoto(String profileId,byte[] profilePhoto) {
        Profile profile = getProfileById(profileId);
        if (profile != null) {
            profile.setProfilePhoto(profilePhoto);
            return profileRepository.save(profile);
        }
        return null;
    }

    public Profile updateProfileBiography(String profileId, String biography) {
        Profile profile = getProfileById(profileId);
        if (profile != null) {
            profile.setBiography(biography);
            return profileRepository.save(profile);
        }
        return null;
    }

    public boolean deleteProfile(String profileId) {
        if (!profileRepository.existsById(profileId)) {
            return false;
        }

        profileRepository.deleteById(profileId);
        return true;
    }

    public void addPostToProfile(String profileId, Post post) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.getPosts().add(post);
            profileRepository.save(profile);
        }
    }

    public void removePostFromProfile(String profileId, Post post) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.getPosts().remove(post);
            profileRepository.save(profile);
        }
    }

    public void addFriendToProfile(String profileId, Profile friend) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.getFriends().add(friend);
            profileRepository.save(profile);
        }
    }

    public void removeFriendFromProfile(String profileId, Profile friend) {
        Optional<Profile> optionalProfile = profileRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();
            profile.getFriends().remove(friend);
            profileRepository.save(profile);
        }
    }
}