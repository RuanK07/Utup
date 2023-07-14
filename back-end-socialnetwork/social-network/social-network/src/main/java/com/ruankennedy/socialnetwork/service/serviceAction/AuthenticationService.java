package com.ruankennedy.socialnetwork.service.serviceAction;


import com.ruankennedy.socialnetwork.config.securityConfig.Token;
import com.ruankennedy.socialnetwork.dto.request.Login;


public interface AuthenticationService {

    Token authenticate(Login loginData);

}
