package com.ruankennedy.socialnetwork.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder(value = {"subtitle", "postPhotos", "postedMoment"})
public class CreatePostDTO {

	@NotBlank
    @JsonProperty(value = "subtitle")
    private String subtitle;

    @NotBlank
    @JsonProperty(value = "postPhotos")
    private List<byte[]> postPhotos;
    
    @JsonProperty(value = "postedMoment")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime postedMoment;
    
}
