package com.ruankennedy.socialnetwork.controller;


import com.ruankennedy.socialnetwork.config.securityConfig.Token;
import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.service.serviceAction.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Primary
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authService; 

    @Override
    @PostMapping
    public ResponseEntity<Token> authenticate(Login loginData) {
        return ResponseEntity.ok().body(authService.authenticate(loginData));
    }

}
