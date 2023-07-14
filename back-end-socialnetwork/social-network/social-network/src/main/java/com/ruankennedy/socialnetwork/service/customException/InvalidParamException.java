package com.ruankennedy.socialnetwork.service.customException;


import java.io.Serial;


public class InvalidParamException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidParamException(String msg) {
        super(msg);
    }

}
