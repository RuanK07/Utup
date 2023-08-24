package com.ruankennedy.socialnetwork.service.serviceAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class ServiceParent {

    @Autowired
    protected PasswordEncoder encoder;

}