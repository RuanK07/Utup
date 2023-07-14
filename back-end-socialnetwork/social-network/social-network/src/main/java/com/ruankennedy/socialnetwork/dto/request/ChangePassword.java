package com.ruankennedy.socialnetwork.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor 
@Getter
@JsonPropertyOrder(value = {"yourPassword", "newPassword"})
public class ChangePassword { 

    @NotBlank 
    @JsonProperty(value = "yourPassword")
    private String password; 

    @NotBlank
    @Size(min = 8, max = 18)
    @JsonProperty(value = "newPassword")
    private String newPassword;

}
