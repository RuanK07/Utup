package com.ruankennedy.socialnetwork.service.serviceAction;


import com.ruankennedy.socialnetwork.config.securityConfig.Token;
import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.service.customException.DatabaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Primary 
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenService tokenService;

    private final AuthenticationManager authManager;

    @Override
    public Token authenticate(Login loginData) {

        try {

            return new Token(tokenService.generateToken(authManager.authenticate(loginData.toConvert())), "Bearer");

        } catch (AuthenticationException e) {
            throw new DatabaseException("E-mail and / or password is / are wrong!");
        }

    }

}
