package com.ruankennedy.socialnetwork.service.businessRule.registerUser;


import com.ruankennedy.socialnetwork.service.customException.PasswordDoesntMatchRegisterUserException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(3)
public class PasswordMatchRegisterUser implements RegisterUserVerification { 

    @Override
    public void verification(RegisterUserArgs args) { 

        String password = args.registerData().getPassword();
        String confirmationPassword = args.registerData().getConfirmationPassword(); 

        if (!password.equals(confirmationPassword)) {
            throw new PasswordDoesntMatchRegisterUserException();
        }

    }

}
