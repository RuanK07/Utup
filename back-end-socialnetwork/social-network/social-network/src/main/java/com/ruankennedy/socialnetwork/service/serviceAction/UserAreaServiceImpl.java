package com.ruankennedy.socialnetwork.service.serviceAction;


import com.ruankennedy.socialnetwork.dto.request.ChangePassword;
import com.ruankennedy.socialnetwork.dto.response.UserDTO;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.businessRule.changePassword.ChangePasswordArgs;
import com.ruankennedy.socialnetwork.service.businessRule.changePassword.ChangePasswordVerification;
import com.ruankennedy.socialnetwork.util.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


@Service 
@RequiredArgsConstructor
@Primary 
public class UserAreaServiceImpl extends ServiceParent implements UserAreaService {

    private final UserRepository userRepository; 

    private final List<ChangePasswordVerification> changePasswordVerifications;

    @Override
    public UserDTO myProfile(User userLogged) {
        return UserMapper.toUserDTO(userLogged);
    }

    @Override
    public String changePassword(ChangePassword cpDTO, User userLogged) {

        changePasswordVerifications.forEach(v -> v.verification(new ChangePasswordArgs(cpDTO, userLogged, encoder)));

        updatePassword(cpDTO, userLogged);

        userRepository.save(userLogged);

        return "Password changed successfully!";

    }

    private void updatePassword(ChangePassword cpDTO, User userLogged) {
        userLogged.setPassword(encoder.encode(cpDTO.getNewPassword()));
    }

}
