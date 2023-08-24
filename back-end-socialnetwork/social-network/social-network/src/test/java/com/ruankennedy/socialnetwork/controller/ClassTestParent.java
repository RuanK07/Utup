package com.ruankennedy.socialnetwork.controller;


import com.ruankennedy.socialnetwork.dto.request.Login;
import com.ruankennedy.socialnetwork.service.serviceAction.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

public abstract class ClassTestParent {

    protected final int forbidden = 403;
    protected final int unauthorized = 401;
    protected final int badRequest = 400;
    protected final int ok = 200;
    protected final int internalServerError = 500;
    protected final int notFound = 404; 
    protected final int created = 201;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; 

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    protected String authenticate(Login loginData) {

        UsernamePasswordAuthenticationToken login = loginData.toConvert();

        Authentication authentication = authManager.authenticate(login);

        String token = tokenService.generateToken(authentication);

        return "Bearer " + token;

    }

}
