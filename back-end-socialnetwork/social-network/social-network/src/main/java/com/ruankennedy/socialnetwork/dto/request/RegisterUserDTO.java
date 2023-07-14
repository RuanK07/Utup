package com.ruankennedy.socialnetwork.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
@JsonPropertyOrder(value = {"nickname", "email", "password", "confirmationPassword"})
public class RegisterUserDTO {

    @NotBlank
    @JsonProperty(value = "nickname")
    private String nickname;

    @NotBlank
    @Email
    @JsonProperty(value = "email")
    private String email;

    @Size(min = 6, max = 18)
    @JsonProperty(value = "password")
    private String password;

    @NotBlank
    @JsonProperty(value = "confirmationPassword")
    private String confirmationPassword;

}
