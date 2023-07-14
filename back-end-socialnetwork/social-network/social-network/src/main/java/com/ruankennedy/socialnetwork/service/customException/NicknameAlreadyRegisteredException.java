package com.ruankennedy.socialnetwork.service.customException;


import java.io.Serial;


public class NicknameAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NicknameAlreadyRegisteredException(String nickname) {
        super("This nickname: " + nickname + " already exists in the system");
    }

}
