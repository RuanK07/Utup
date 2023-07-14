package com.ruankennedy.socialnetwork.config.exceptionConfig.handler;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


public class UnauthorizedHandler extends AuthorizationAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {

        if (!response.isCommitted()) {


            status = HttpStatus.UNAUTHORIZED.value();
            error = "Unhautorized";
            messageError = "In order to access this recource, you have to be logged in the system";

            responseClient(request, response, status, error, messageError);

        }

    }

}