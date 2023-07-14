package com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter;


import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class NoRoleName extends FindUsersByRoleNameVerification {

    public NoRoleName(FindUsersByRoleNameVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<User> verification(FindUsersArgs args) {

        String roleName = args.roleName();
        Pageable pageable = args.pageable();
        UserRepository userRepository = args.userRepository();
        if (roleName == null) {
            return userRepository.findAll(pageable);
        }

        return nextOne.verification(args);

    }

}
