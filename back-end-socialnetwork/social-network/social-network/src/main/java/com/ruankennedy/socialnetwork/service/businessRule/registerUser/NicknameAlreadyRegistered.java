package com.ruankennedy.socialnetwork.service.businessRule.registerUser;


import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.NicknameAlreadyRegisteredException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@Order(1)
public class NicknameAlreadyRegistered implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        String nickname = args.registerData().getNickname();
        UserRepository userRepository = args.userRepository();

        Optional<User> user = userRepository.findByNickname(nickname);

        if (user.isPresent()) {
            throw new NicknameAlreadyRegisteredException(nickname);
        }

    }

}
