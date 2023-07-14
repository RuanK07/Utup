package com.ruankennedy.socialnetwork.service.businessRule.registerUser;


import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.customException.EmailAlreadyRegisteredException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@Order(2)
public class EmailAlreadyRegistered implements RegisterUserVerification {

    @Override
    public void verification(RegisterUserArgs args) {

        String email = args.registerData().getEmail();

        UserRepository userRepository = args.userRepository();

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            throw new EmailAlreadyRegisteredException(email);
        }

    }

}
