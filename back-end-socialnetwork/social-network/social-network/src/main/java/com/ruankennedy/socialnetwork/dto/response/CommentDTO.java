package com.ruankennedy.socialnetwork.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ruankennedy.socialnetwork.model.Comment;

import lombok.Getter;

@JsonPropertyOrder(value = {"comment"})
@Getter
public class CommentDTO {

    @JsonProperty(value = "comment")
    private String comment;

    public CommentDTO(Comment comment) {
        this.comment = comment.getComment();
    }

}
