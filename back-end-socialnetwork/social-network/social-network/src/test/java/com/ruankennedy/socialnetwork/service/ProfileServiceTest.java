package com.ruankennedy.socialnetwork.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;

public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    private ProfileService profileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        profileService = new ProfileService(profileRepository);
    }

    @Test
    public void testCreateProfile() {
        Profile profile = new Profile();
        profile.setBiography("Test Biography");

        when(profileRepository.save(profile)).thenReturn(profile);

        Profile createdProfile = profileService.createProfile(profile);

        assertNotNull(createdProfile.getId());
        assertEquals("Test Biography", createdProfile.getBiography());

        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testGetProfileById() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(profile));

        Profile retrievedProfile = profileService.getProfileById("profile-1");

        assertNotNull(retrievedProfile);
        assertEquals("profile-1", retrievedProfile.getId());
        assertEquals("Test Biography", retrievedProfile.getBiography());

        verify(profileRepository, times(1)).findById("profile-1");
    }

    @Test
    public void testGetProfileById_NonexistentProfile() {
        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        Profile retrievedProfile = profileService.getProfileById("nonexistent-profile");

        assertNull(retrievedProfile);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
    }

    @Test
    public void testGetAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile());
        profiles.add(new Profile());

        when(profileRepository.findAll()).thenReturn(profiles);

        List<Profile> retrievedProfiles = profileService.getAllProfiles();

        assertEquals(2, retrievedProfiles.size());

        verify(profileRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateProfile() {
        Profile existingProfile = new Profile();
        existingProfile.setId("profile-1");
        existingProfile.setBiography("Existing Biography");

        Profile updatedProfile = new Profile();
        updatedProfile.setId("profile-1");
        updatedProfile.setBiography("Updated Biography");

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(existingProfile));
        when(profileRepository.save(existingProfile)).thenReturn(updatedProfile);

        Profile resultProfile = profileService.updateProfile("profile-1", updatedProfile);

        assertNotNull(resultProfile);
        assertEquals("profile-1", resultProfile.getId());
        assertEquals("Updated Biography", resultProfile.getBiography());

        verify(profileRepository, times(1)).findById("profile-1");
        verify(profileRepository, times(1)).save(existingProfile);
    }

    @Test
    public void testUpdateProfile_NonexistentProfile() {
        Profile updatedProfile = new Profile();
        updatedProfile.setId("nonexistent-profile");
        updatedProfile.setBiography("Updated Biography");

        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        Profile resultProfile = profileService.updateProfile("nonexistent-profile", updatedProfile);

        assertNull(resultProfile);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    public void testUpdateProfilePhoto() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        byte[] photo = {1, 2, 3, 4, 5};

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenReturn(profile);

        Profile resultProfile = profileService.updateProfilePhoto("profile-1", photo);

        assertNotNull(resultProfile);
        assertEquals("profile-1", resultProfile.getId());
        assertArrayEquals(photo, resultProfile.getProfilePhoto());

        verify(profileRepository, times(1)).findById("profile-1");
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testUpdateProfilePhoto_NonexistentProfile() {
        byte[] photo = {1, 2, 3, 4, 5};

        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        Profile resultProfile = profileService.updateProfilePhoto("nonexistent-profile", photo);

        assertNull(resultProfile);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    public void testUpdateProfileBiography() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenReturn(profile);

        Profile resultProfile = profileService.updateProfileBiography("profile-1", "Updated Biography");

        assertNotNull(resultProfile);
        assertEquals("profile-1", resultProfile.getId());
        assertEquals("Updated Biography", resultProfile.getBiography());

        verify(profileRepository, times(1)).findById("profile-1");
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testUpdateProfileBiography_NonexistentProfile() {
        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        Profile resultProfile = profileService.updateProfileBiography("nonexistent-profile", "Updated Biography");

        assertNull(resultProfile);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    public void testDeleteProfile() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        when(profileRepository.existsById("profile-1")).thenReturn(true);

        boolean result = profileService.deleteProfile("profile-1");

        assertTrue(result);

        verify(profileRepository, times(1)).existsById("profile-1");
        verify(profileRepository, times(1)).deleteById("profile-1");
    }

    @Test
    public void testDeleteProfile_NonexistentProfile() {
        when(profileRepository.existsById("nonexistent-profile")).thenReturn(false);

        boolean result = profileService.deleteProfile("nonexistent-profile");

        assertFalse(result);

        verify(profileRepository, times(1)).existsById("nonexistent-profile");
        verify(profileRepository, never()).deleteById(anyString());
    }

    @Test
    public void testAddPostToProfile() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        Post post = new Post();
        post.setSubtitle("Test Post");

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenReturn(profile);

        profileService.addPostToProfile("profile-1", post);

        assertTrue(profile.getPosts().contains(post));

        verify(profileRepository, times(1)).findById("profile-1");
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testAddPostToProfile_NonexistentProfile() {
        Post post = new Post();
        post.setSubtitle("Test Post");

        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        profileService.addPostToProfile("nonexistent-profile", post);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    public void testRemovePostFromProfile() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        Post post = new Post();
        post.setSubtitle("Test Post");

        profile.getPosts().add(post);

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenReturn(profile);

        profileService.removePostFromProfile("profile-1", post);

        assertFalse(profile.getPosts().contains(post));

        verify(profileRepository, times(1)).findById("profile-1");
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testRemovePostFromProfile_NonexistentProfile() {
        Post post = new Post();
        post.setSubtitle("Test Post");

        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        profileService.removePostFromProfile("nonexistent-profile", post);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    public void testAddFriendToProfile() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        Profile friend = new Profile();
        friend.setId("friend-1");
        friend.setBiography("Friend Biography");

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenReturn(profile);

        profileService.addFriendToProfile("profile-1", friend);

        assertTrue(profile.getFriends().contains(friend));

        verify(profileRepository, times(1)).findById("profile-1");
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testAddFriendToProfile_NonexistentProfile() {
        Profile friend = new Profile();
        friend.setId("friend-1");
        friend.setBiography("Friend Biography");

        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        profileService.addFriendToProfile("nonexistent-profile", friend);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
        verify(profileRepository, never()).save(any(Profile.class));
    }

    @Test
    public void testRemoveFriendFromProfile() {
        Profile profile = new Profile();
        profile.setId("profile-1");
        profile.setBiography("Test Biography");

        Profile friend = new Profile();
        friend.setId("friend-1");
        friend.setBiography("Friend Biography");

        profile.getFriends().add(friend);

        when(profileRepository.findById("profile-1")).thenReturn(Optional.of(profile));
        when(profileRepository.save(profile)).thenReturn(profile);

        profileService.removeFriendFromProfile("profile-1", friend);

        assertFalse(profile.getFriends().contains(friend));

        verify(profileRepository, times(1)).findById("profile-1");
        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testRemoveFriendFromProfile_NonexistentProfile() {
        Profile friend = new Profile();
        friend.setId("friend-1");
        friend.setBiography("Friend Biography");

        when(profileRepository.findById("nonexistent-profile")).thenReturn(Optional.empty());

        profileService.removeFriendFromProfile("nonexistent-profile", friend);

        verify(profileRepository, times(1)).findById("nonexistent-profile");
        verify(profileRepository, never()).save(any(Profile.class));
    }
}