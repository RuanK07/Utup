package com.ruankennedy.socialnetwork.service.userService;


import com.ruankennedy.socialnetwork.dto.response.UserMonitoringDTO;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.ResourceNotFoundException;
import com.ruankennedy.socialnetwork.service.serviceAction.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class FindUsersTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional 
    void findUsersParameterAdministrator() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<UserMonitoringDTO> usersDto = userService.findAll(pageable, "ADMIN");

        boolean containsRoleUser = false;

        for (UserMonitoringDTO userDTO : usersDto) {

            if (!userDTO.getRolesDTO().get(0).getRoleName().equals("ROLE_ADMIN")) {
                containsRoleUser = true;
            }

            Assertions.assertFalse(containsRoleUser);

        }

    }

    @Test
    @Transactional
    void findUsersParameterUser() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<UserMonitoringDTO> usersDto = userService.findAll(pageable, "USER");

        boolean containsRoleAdministrator = false;

        for (UserMonitoringDTO userDTO : usersDto) {

            if (!userDTO.getRolesDTO().get(0).getRoleName().equals("ROLE_USER")) {
                containsRoleAdministrator = true;
            }

            Assertions.assertFalse(containsRoleAdministrator);

        }

    }

    @Test
    @Transactional
    void findById() {

        User user = userRepository.findByEmail("admin@hotmail.com")
                .orElseThrow(()-> new ResourceNotFoundException("The user wasn't found on database\""));
                                                                                                         
        String id = user.getId();

        UserMonitoringDTO userDTO = userService.findById(id);

        Assertions.assertEquals(id, userDTO.getId());

    }

}

