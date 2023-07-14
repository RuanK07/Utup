package com.ruankennedy.socialnetwork.util;

import com.ruankennedy.socialnetwork.dto.request.RegisterUserDTO;
import com.ruankennedy.socialnetwork.dto.response.RoleDTO;
import com.ruankennedy.socialnetwork.dto.response.UserDTO;
import com.ruankennedy.socialnetwork.dto.response.UserMonitoringDTO;
import com.ruankennedy.socialnetwork.model.Role;
import com.ruankennedy.socialnetwork.model.User;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static User toUserEntity(RegisterUserDTO registerData, PasswordEncoder encoder) {

        return User.builder()
                .nickname(registerData.getNickname())
                .email(registerData.getEmail())
                .password(encoder.encode(registerData.getPassword()))
                .build(); 

    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user);
    }

    public static UserMonitoringDTO toUserMonitoringDTO(User user) {
        return new UserMonitoringDTO(user);
    }

    public static Page<UserMonitoringDTO> toUserMonitoringDTO(Page<User> users) {
        return users.map(UserMonitoringDTO::new);
    }

    public static List<RoleDTO> toRoleDTO(List<Role> roles) {
        return roles.stream().map(RoleDTO::new).collect(Collectors.toList());
    }

}
