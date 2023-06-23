package com.ruankennedy.socialnetwork.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonPropertyOrder(value = {"subtitle", "postPhotos"})
public class CreatePostRequestDTO {

    @NotBlank
    @JsonProperty(value = "subtitle")
    private String subtitle;

    @NotBlank
    @JsonProperty(value = "postPhotos")
    private List<byte[]> postPhotos;

}
