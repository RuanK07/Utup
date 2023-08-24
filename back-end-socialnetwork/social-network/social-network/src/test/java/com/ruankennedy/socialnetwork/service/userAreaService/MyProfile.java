package com.ruankennedy.socialnetwork.service.userAreaService;


import com.ruankennedy.socialnetwork.dto.response.UserDTO;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.serviceAction.UserAreaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyProfile {

    @Autowired
    private UserAreaService userAreaService;

    @Autowired
    private UserRepository userRepository;
    @Test
    void loggedUserData() {

        User user = userRepository.findByEmail("user2@hotmail.com").get();

        UserDTO userDTO = userAreaService.myProfile(user); 

        Assertions.assertEquals(user.getNickname(), userDTO.getNickname());
        Assertions.assertEquals(user.getUsername(), userDTO.getEmail());

    }

}
