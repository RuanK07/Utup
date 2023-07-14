package com.ruankennedy.socialnetwork.service.businessRule.changePassword;


import com.ruankennedy.socialnetwork.service.customException.DatabaseException;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Order(1)
public class PasswordMatchsDataBasePassword implements ChangePasswordVerification {

    @Override
    public void verification(ChangePasswordArgs args) { 

        String passwordDTO = args.changePasswordDTO().getPassword(); 

        String passwordDataBase = args.user().getPassword(); 

        PasswordEncoder encoder = args.encoder(); 


        if (!encoder.matches(passwordDTO,passwordDataBase)) { 
            throw new DatabaseException("The password is not correct (not match)"); 
        }

    }

}
