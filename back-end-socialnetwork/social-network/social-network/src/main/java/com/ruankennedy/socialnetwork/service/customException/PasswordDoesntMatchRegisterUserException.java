package com.ruankennedy.socialnetwork.service.customException;


import java.io.Serial;


public class PasswordDoesntMatchRegisterUserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PasswordDoesntMatchRegisterUserException() {
        super("The passwords don't match");
    }

}
