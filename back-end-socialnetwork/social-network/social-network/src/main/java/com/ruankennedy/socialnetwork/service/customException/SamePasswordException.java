package com.ruankennedy.socialnetwork.service.customException;


import java.io.Serial;


public class SamePasswordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SamePasswordException() {
        super("Your new password cannot be equal the last one");
    }

}
