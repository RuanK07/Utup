package com.ruankennedy.socialnetwork.service.serviceAction;


import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.ruankennedy.socialnetwork.dto.request.RegisterUserDTO;
import com.ruankennedy.socialnetwork.dto.response.UserMonitoringDTO;
import com.ruankennedy.socialnetwork.enumerated.RoleName;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.Role;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.repository.RoleRepository;
import com.ruankennedy.socialnetwork.repository.UserRepository;
import com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter.FindUsersArgs;
import com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter.FindUsersByRoleNameVerification;
import com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter.InvalidRoleName;
import com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter.NoRoleName;
import com.ruankennedy.socialnetwork.service.businessRule.findUsersByParameter.ValidRoleName;
import com.ruankennedy.socialnetwork.service.businessRule.registerUser.RegisterUserArgs;
import com.ruankennedy.socialnetwork.service.businessRule.registerUser.RegisterUserVerification;
import com.ruankennedy.socialnetwork.service.customException.ResourceNotFoundException;
import com.ruankennedy.socialnetwork.util.UserMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl extends ServiceParent implements UserService { 

    private final UserRepository userRepository;
    
    private final ProfileService profileService;
    
    private final RoleRepository roleRepository;

    private final List<RegisterUserVerification> registerUserVerifications;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with this e-mail : " + username));

    }

    @Override
    @CacheEvict(value = "usersList", allEntries = true)
    public UserMonitoringDTO register(RegisterUserDTO registerData) { 
        registerUserVerifications.forEach(v -> v.verification(new RegisterUserArgs(registerData, userRepository)));

        User user = UserMapper.toUserEntity(registerData, encoder);
        user.addRole(returnRole(RoleName.ROLE_USER));

        User registeredUser = userRepository.save(user);

        Profile profile = Profile.builder()
                .user(registeredUser)
                .build();
        profileService.createProfile(profile);

        return UserMapper.toUserMonitoringDTO(registeredUser);
    }

    @Override
    @Cacheable(value = "usersList")
    public Page<UserMonitoringDTO> findAll(Pageable pageable, String roleName) {
        return UserMapper.toUserMonitoringDTO(verifyParametersToReturnCorrectPage(pageable, roleName));
    }

    @Override
    public UserMonitoringDTO findById(String id) {
        return UserMapper.toUserMonitoringDTO(returnUser(id));
    }

    private Role returnRole(RoleName name) {
        return roleRepository.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException("The role : " + name + " wasn't found in database"));
    }

    private Page<User> verifyParametersToReturnCorrectPage(Pageable pageable, String roleName) {
        FindUsersByRoleNameVerification verification = new NoRoleName(
                new ValidRoleName(
                        new InvalidRoleName()));

        return verification.verification(new FindUsersArgs(userRepository, pageable, roleName));

    }
    
    @Override
    public User getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundException("User not found with this email: " + nickname));
    }
    
    private User returnUser(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("The user id: " + id + " wasn't found on database"));
    }

}
