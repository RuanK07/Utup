package com.ruankennedy.socialnetwork.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonPropertyOrder(value = {"biography", "postPhotos"})
public class UpdateProfileRequestDTO {

    @NotBlank
    @JsonProperty(value = "biography")
    private String biography;

    @NotBlank
    @JsonProperty(value = "profilePhoto")
    private byte[] postPhotos;

}