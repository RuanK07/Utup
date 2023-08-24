package com.ruankennedy.socialnetwork.service.serviceAction;


import com.ruankennedy.socialnetwork.dto.request.RegisterUserDTO;
import com.ruankennedy.socialnetwork.dto.response.UserMonitoringDTO;
import com.ruankennedy.socialnetwork.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserMonitoringDTO register(RegisterUserDTO registerData);

    Page<UserMonitoringDTO> findAll(Pageable pageable, String roleName);

    UserMonitoringDTO findById(String id);
    
    User getUserByNickname(String nickname);
}
