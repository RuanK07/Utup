package com.ruankennedy.socialnetwork.service.businessRule.changePassword;


import com.ruankennedy.socialnetwork.service.customException.SamePasswordException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(2)
public class DifferentPassword implements ChangePasswordVerification {

    @Override
    public void verification(ChangePasswordArgs args) {

        String newPassword = args.changePasswordDTO().getNewPassword();

        String passwordDataBase = args.changePasswordDTO().getPassword();

        if(newPassword.equals(passwordDataBase)) {
            throw new SamePasswordException();
        }

    }

}
