package com.ruankennedy.socialnetwork.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonPropertyOrder(value = {"subtitle"})
public class UpdatePostRequestDTO {

    @NotBlank
    @JsonProperty(value = "subtitle")
    private String subtitle;

}