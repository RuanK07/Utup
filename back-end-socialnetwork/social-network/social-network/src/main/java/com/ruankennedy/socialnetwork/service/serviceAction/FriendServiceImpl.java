package com.ruankennedy.socialnetwork.service.serviceAction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ruankennedy.socialnetwork.model.Friend;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.FriendRepository;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Primary
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    
    private final ProfileRepository profileRepository;
    
    private final UserRepository userRepository;

    @Override
    public Friend createFriend(User userLogged, String nickname) {
    	
    	Optional<User> userOptional = userRepository.findByNickname(nickname);
        User user = userOptional.get();
    	
        Profile requesterProfile = profileRepository.findByUserId(userLogged.getId());
        Profile targetProfile = profileRepository.findByUserId(user.getId());

        Friend requesterFriend = Friend.builder()
                .friendStart(LocalDateTime.now())
                .profile(requesterProfile)
                .targetProfile(targetProfile)
                .build();

        Friend targetFriend = Friend.builder()
                .friendStart(LocalDateTime.now())
                .profile(targetProfile)
                .targetProfile(requesterProfile)
                .build();

        friendRepository.save(requesterFriend);
        friendRepository.save(targetFriend);

        return requesterFriend;
    }

    @Override
    public List<Friend> getFriendsByUserNickname(String nickname) {
    	Optional<User> userOptional = userRepository.findByNickname(nickname);
        User user = userOptional.get();
        Profile profile = user.getProfile();
        return friendRepository.findByProfileId(profile.getId());
    }

}