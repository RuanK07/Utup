package com.ruankennedy.socialnetwork.dto.response;


import com.ruankennedy.socialnetwork.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;


@JsonPropertyOrder(value = {"nickname", "email", "registrationMoment"})
@Getter
public class UserDTO { 

    @JsonProperty(value = "nickname")  
    private final String nickname;

    @JsonProperty(value = "email")
    private final String email;

    @JsonProperty(value = "registrationMoment")  
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime registrationMoment;

    public UserDTO(User user) {
        this.nickname = user.getNickname();
        this.email = user.getUsername();
        this.registrationMoment = user.getRegistrationMoment();
    }

}
