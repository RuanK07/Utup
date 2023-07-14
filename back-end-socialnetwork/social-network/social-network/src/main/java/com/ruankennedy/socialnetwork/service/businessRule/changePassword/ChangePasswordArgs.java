package com.ruankennedy.socialnetwork.service.businessRule.changePassword;


import com.ruankennedy.socialnetwork.dto.request.ChangePassword;
import com.ruankennedy.socialnetwork.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public record ChangePasswordArgs(ChangePassword changePasswordDTO, User user,
                                 PasswordEncoder encoder) {

}
