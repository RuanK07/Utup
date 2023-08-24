package com.ruankennedy.socialnetwork.service.userService;


import com.ruankennedy.socialnetwork.dto.request.RegisterUserDTO;
import com.ruankennedy.socialnetwork.dto.response.UserMonitoringDTO;
import com.ruankennedy.socialnetwork.enumerated.RoleName;
import com.ruankennedy.socialnetwork.service.serviceAction.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegistrationUserTest {

    @Autowired
    private UserService userService; 

    @Test
    void returnRegisterData() {

        RegisterUserDTO userRequestDTO = new RegisterUserDTO("paulo", "paulo@hotmail.com", "1234567", "1234567");

        UserMonitoringDTO userResponseDTO = userService.register(userRequestDTO); 

        Assertions.assertEquals(userRequestDTO.getNickname(), userResponseDTO.getNickname()); 
        Assertions.assertEquals(userRequestDTO.getEmail(), userResponseDTO.getEmail());
        Assertions.assertEquals(userResponseDTO.getRolesDTO().get(0).getRoleName(), RoleName.ROLE_USER.name());

    }

}
