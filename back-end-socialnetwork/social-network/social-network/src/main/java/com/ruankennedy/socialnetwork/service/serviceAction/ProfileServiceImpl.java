package com.ruankennedy.socialnetwork.service.serviceAction;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.ruankennedy.socialnetwork.dto.request.ChangeBiography;
import com.ruankennedy.socialnetwork.dto.request.ChangeProfilePhoto;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }
    
    @Override
    public Profile getProfileById(String profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found"));
    }

    @Override
    public Profile getProfileByUserNickname(String nickname) {
        Profile profile = profileRepository.findByUserNickname(nickname);
        return profile;
    }

    @Override
    public Profile updateProfilePhoto(User userLogged, ChangeProfilePhoto request) {
        Profile profile = profileRepository.findByUserId(userLogged.getId());
        profile.setProfilePhoto(request.getProfilePhoto());
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateBiography(User userLogged, ChangeBiography request) {
        Profile profile = profileRepository.findByUserId(userLogged.getId());
        profile.setBiography(request.getBiography());
        return profileRepository.save(profile);
    }
}