package com.ruankennedy.socialnetwork.service.serviceAction;


import org.springframework.security.core.Authentication;


public interface TokenService {

    String generateToken(Authentication authentication);

    String getSubject(String token); 

}
