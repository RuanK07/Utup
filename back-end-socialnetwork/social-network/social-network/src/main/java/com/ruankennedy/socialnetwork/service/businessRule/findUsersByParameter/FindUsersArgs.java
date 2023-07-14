package com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter;


import com.ruankennedy.socialnetwork.repository.UserRepository;
import org.springframework.data.domain.Pageable;

public record FindUsersArgs(UserRepository userRepository, Pageable pageable,
                            String roleName) {

}
