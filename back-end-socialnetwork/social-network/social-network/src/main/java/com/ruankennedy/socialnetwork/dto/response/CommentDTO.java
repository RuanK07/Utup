package com.ruankennedy.socialnetwork.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ruankennedy.socialnetwork.model.Comment;

import lombok.Getter;

@JsonPropertyOrder(value = {"comment, commentedMoment"})
@Getter
public class CommentDTO {

    @JsonProperty(value = "comment")
    private String comment;
    
    @JsonProperty(value = "commentedMoment")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime commentedMoment;

    public CommentDTO(Comment comment) {
        this.comment = comment.getComment();
        this.commentedMoment = comment.getCommentedMoment();
    }

}
