package com.ruankennedy.socialnetwork.config.securityConfig;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
@JsonPropertyOrder(value = {"token", "type"})
public class Token { 

    @JsonProperty(value = "token")
    private String token;

    @JsonProperty(value = "type")
    private String type;

}
