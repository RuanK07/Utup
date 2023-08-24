package com.ruankennedy.socialnetwork.service.serviceAction;

import com.ruankennedy.socialnetwork.dto.request.ChangeBiography;
import com.ruankennedy.socialnetwork.dto.request.ChangeProfilePhoto;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;

public interface ProfileService {
	Profile createProfile(Profile profile);
	Profile getProfileById(String profileId);
    Profile getProfileByUser(User userLogged);
    Profile updateProfilePhoto(User userLogged, ChangeProfilePhoto request);
    Profile updateBiography(User userLogged, ChangeBiography request);
}