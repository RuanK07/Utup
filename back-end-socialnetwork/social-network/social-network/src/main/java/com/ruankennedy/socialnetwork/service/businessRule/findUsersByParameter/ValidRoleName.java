package com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter;


import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class ValidRoleName extends FindUsersByRoleNameVerification {

    public ValidRoleName(FindUsersByRoleNameVerification nextOne) {
        super(nextOne);
    }

    @Override
    public Page<User> verification(FindUsersArgs args) {

        String roleName = args.roleName();
        Pageable pageable = args.pageable();
        UserRepository userRepository = args.userRepository();

        String role = "ROLE_" + roleName.toUpperCase();

        boolean validParameter = validParameter(role);

        if (validParameter) {
            return userRepository.findByRole(pageable, role);
        }

        return nextOne.verification(args);

    }

    private boolean validParameter (String role) {
        return role.equalsIgnoreCase("ROLE_ADMIN") ||  role.equalsIgnoreCase("ROLE_USER");
    }

}
