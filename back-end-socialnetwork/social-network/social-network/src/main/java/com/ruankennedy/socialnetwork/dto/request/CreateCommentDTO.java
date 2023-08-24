package com.ruankennedy.socialnetwork.dto.request;

import java.time.LocalDateTime;

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
@JsonPropertyOrder(value = {"comment", "commentedMoment"})
public class CreateCommentDTO {

    @NotBlank
    @JsonProperty(value = "comment")
    private String comment;
    
    @JsonProperty(value = "commentedMoment")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime commentedMoment;

}
