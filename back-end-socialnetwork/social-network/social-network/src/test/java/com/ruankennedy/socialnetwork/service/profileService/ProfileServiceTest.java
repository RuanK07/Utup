package com.ruankennedy.socialnetwork.service.profileService;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ruankennedy.socialnetwork.dto.request.ChangeBiography;
import com.ruankennedy.socialnetwork.dto.request.ChangeProfilePhoto;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.ResourceNotFoundException;
import com.ruankennedy.socialnetwork.service.serviceAction.ProfileServiceImpl;

import jakarta.transaction.Transactional;


@SpringBootTest
public class ProfileServiceTest {

	@Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileServiceImpl profileService;
    
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testGetProfileById() {
    	
    	User user = userRepository.findByEmail("admin@hotmail.com") 
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));
    	
    	String userId = user.getId();
    	
        Profile profile = profileRepository.findByUserId(userId);
        
        String profileId = profile.getId();
        
        Profile profile2 = profileService.getProfileById(profileId);
        
        Assertions.assertEquals(profileId, profile2.getId());
    }
    
    @Test
    @Transactional
    public void testUpdateProfilePhoto() {

    	User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));

        byte[] newProfilePhoto = loadProfilePhotoAsByteArray("static/images/setup2.png");

        ChangeProfilePhoto request = new ChangeProfilePhoto(newProfilePhoto);

        Profile updatedProfile = profileService.updateProfilePhoto(user, request);

        Assertions.assertArrayEquals(newProfilePhoto, updatedProfile.getProfilePhoto());
    }

    @Test
    @Transactional
    public void testUpdateBiography() {

    	User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));

        String newBiography = "NovaBiografia";

        ChangeBiography request = new ChangeBiography(newBiography);

        Profile updatedProfile = profileService.updateBiography(user, request);

        Assertions.assertEquals(newBiography, updatedProfile.getBiography());
    } 
    
    private byte[] loadProfilePhotoAsByteArray(String filePath) {
        try {
            Resource resource = new ClassPathResource(filePath);
            return Files.readAllBytes(resource.getFile().toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}