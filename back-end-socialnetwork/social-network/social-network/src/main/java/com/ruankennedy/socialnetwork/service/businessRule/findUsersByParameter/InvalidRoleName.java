package com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter;


import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.service.customException.InvalidParamException;
import org.springframework.data.domain.Page;

public class InvalidRoleName extends FindUsersByRoleNameVerification {

    public InvalidRoleName() {
        super(null);
    }

    @Override
    public Page<User> verification(FindUsersArgs args) { 

        String roleName = args.roleName();

        throw new InvalidParamException("This parameter (role) : { " + roleName + " } is invalid");

    }

}
