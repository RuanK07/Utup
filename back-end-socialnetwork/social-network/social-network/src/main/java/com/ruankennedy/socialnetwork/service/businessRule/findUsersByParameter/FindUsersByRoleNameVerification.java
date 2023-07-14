package com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter;


import com.ruankennedy.socialnetwork.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;


@AllArgsConstructor
public abstract class FindUsersByRoleNameVerification {

    protected FindUsersByRoleNameVerification nextOne;

    public abstract Page<User> verification(FindUsersArgs args);

}
