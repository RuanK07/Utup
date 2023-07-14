package com.ruankennedy.socialnetwork.service.serviceAction;


import com.ruankennedy.socialnetwork.dto.request.ChangePassword;
import com.ruankennedy.socialnetwork.dto.response.UserDTO;
import com.ruankennedy.socialnetwork.model.User;


public interface UserAreaService {

    UserDTO myProfile(User userLogged);

    String changePassword(ChangePassword cpDTO, User userLogged);

}
