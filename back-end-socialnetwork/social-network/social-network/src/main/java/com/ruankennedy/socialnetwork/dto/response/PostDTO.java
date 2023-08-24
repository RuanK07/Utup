package com.ruankennedy.socialnetwork.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ruankennedy.socialnetwork.model.Post;

import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder(value = {"subtitle", "postPhoto", "postedMoment"})
@Getter
@Setter
public class PostDTO {

    @JsonProperty(value = "subtitle")
    private final String subtitle;

    @JsonProperty(value = "postPhoto")
    private final List<byte[]> postPhoto;
    
    @JsonProperty(value = "postedMoment")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime postedMoment;

    public PostDTO(Post post) {
        this.subtitle = post.getSubtitle();
        this.postPhoto = post.getPostPhoto();
        this.postedMoment = post.getPostedMoment();
    }

}
