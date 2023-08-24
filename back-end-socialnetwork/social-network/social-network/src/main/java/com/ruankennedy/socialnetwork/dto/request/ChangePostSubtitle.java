package com.ruankennedy.socialnetwork.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder(value = {"subtitle"})
public class ChangePostSubtitle {

    @NotBlank
    @JsonProperty(value = "subtitle")
    private String subtitle;

}