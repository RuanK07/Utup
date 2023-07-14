package com.ruankennedy.socialnetwork.service.businessRule.registerUser;


import com.ruankennedy.socialnetwork.dto.request.RegisterUserDTO;
import com.ruankennedy.socialnetwork.repository.UserRepository;


public record RegisterUserArgs(RegisterUserDTO registerData,
                               UserRepository userRepository) {

}
