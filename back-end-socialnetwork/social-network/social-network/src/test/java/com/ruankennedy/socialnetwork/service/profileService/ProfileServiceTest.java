package com.ruankennedy.socialnetwork.service.profileService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ruankennedy.socialnetwork.dto.request.ChangeBiography;
import com.ruankennedy.socialnetwork.dto.request.ChangeProfilePhoto;
import com.ruankennedy.socialnetwork.dto.response.ProfileDTO;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.serviceAction.ProfileServiceImpl;

@SpringBootTest
public class ProfileServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private ProfileRepository profileRepository;
    
    @InjectMocks
    private ProfileServiceImpl profileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void changeProfilePhotoTest() throws IOException {
        String userId = "user1";
        String imagePath = "/social-network/src/test/java/com/ruankennedy/socialnetwork/image/test.png";
        Path path = Path.of(imagePath);
        byte[] photoData = Files.readAllBytes(path);
        ChangeProfilePhoto changeProfilePhoto = new ChangeProfilePhoto(photoData);
        
        User user = new User();
        Profile profile = new Profile();
        user.setProfile(profile);
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        
        profileService.changeProfilePhoto(userId, changeProfilePhoto);
        
        verify(userRepository).findById(userId);
        verify(profileRepository).save(profile);
        
        Assertions.assertEquals(changeProfilePhoto.getProfilePhoto(), profile.getProfilePhoto());
    }

    @Test
    public void changeBiographyTest() {
        String userId = "user1";
        ChangeBiography changeBiography = new ChangeBiography("New biography");
        
        User user = new User();
        Profile profile = new Profile();
        user.setProfile(profile);
        
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        
        profileService.changeBiography(userId, changeBiography);
        
        verify(userRepository).findById(userId);
        verify(profileRepository).save(profile);
        
        Assertions.assertEquals(changeBiography.getBiography(), profile.getBiography());
    }

    @Test
    public void getProfileByNicknameTest() {
        String nickname = "user1";
        
        User user = new User();
        Profile profile = new Profile();
        user.setProfile(profile);
        
        when(userRepository.findByNickname(nickname)).thenReturn(Optional.of(user));
        
        ProfileDTO result = profileService.getProfileByNickname(nickname);
        
        verify(userRepository).findByNickname(nickname);
        
        Assertions.assertEquals(profile.getProfilePhoto(), result.getProfilePhoto());
        Assertions.assertEquals(profile.getBiography(), result.getBiography());
    }
}