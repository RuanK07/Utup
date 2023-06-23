package com.ruankennedy.socialnetwork.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;

import lombok.Getter;

@JsonPropertyOrder(value = {"subtitle", "postPhoto", "postedMoment", "commentIds"})
@Getter
// Estou garantindo a ordem dos atributos no JSON.
public class PostDTO { // DTO que será retornado pro client, representa o usuário (perfil na visão do usuario).

    @JsonProperty(value = "subtitle")  // Dando nome para o atributo no JSON
    private final String subtitle; // username do usuario

    @JsonProperty(value = "postPhoto")  // Dando nome para o atributo no JSON
    private final List<byte[]> postPhoto; // username do usuario
    
    @JsonProperty(value = "postedMoment")  // Dando nome para o atributo no JSON
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private final LocalDateTime postedMoment; // momento de registro do usuário
    
    @JsonProperty(value = "commentIds")
    private List<String> commentIds;

    public PostDTO(Post post) {
        this.subtitle = post.getSubtitle();
        this.postPhoto = post.getPostPhoto();
        this.postedMoment = post.getPostedMoment();
        this.commentIds = post.getComments().stream().map(Comment::getId).collect(Collectors.toList());
    }

}
