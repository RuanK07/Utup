package com.ruankennedy.socialnetwork.service.friendService;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ruankennedy.socialnetwork.model.Friend;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.ResourceNotFoundException;
import com.ruankennedy.socialnetwork.service.serviceAction.FriendService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class FriendServiceTest {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    @Transactional
    void testCreateFriend() {

        User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(() -> new ResourceNotFoundException("The user wasn't found on database"));

        Profile profile = profileRepository.findByUserId(user.getId());

        Friend createdFriend = friendService.createFriend(user, "user2");
        
        Assertions.assertNotNull(createdFriend);
        Assertions.assertNotNull(createdFriend.getId());
        Assertions.assertNotNull(createdFriend.getFriendStart());
        Assertions.assertEquals(profile, createdFriend.getProfile());
    }

    @Test
    @Transactional
    void testGetFriendsByUserNickname() {
    	
        List<Friend> friends = friendService.getFriendsByUserNickname("admin");

        Assertions.assertNotNull(friends);
        Assertions.assertFalse(friends.isEmpty());

    }

}