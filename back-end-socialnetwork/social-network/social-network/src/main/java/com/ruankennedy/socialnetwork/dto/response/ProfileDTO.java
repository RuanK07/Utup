package com.ruankennedy.socialnetwork.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;

import lombok.Getter;

@JsonPropertyOrder(value = {"profilePhoto", "biography", "postIds", "friendIds"})
@Getter
public class ProfileDTO {

    @JsonProperty(value = "profilePhoto")
    private final byte[] profilePhoto;

    @JsonProperty(value = "biography")
    private final String biography;
    
    @JsonProperty(value = "postIds")
    private List<String> postIds;
    
    @JsonProperty(value = "friendIds")
    private List<String> friendIds;

    public ProfileDTO(Profile profile) {
        this.profilePhoto = profile.getProfilePhoto();
        this.biography = profile.getBiography();
        this.postIds = profile.getPosts().stream().map(Post::getId).collect(Collectors.toList());
        this.friendIds = profile.getFriends().stream().map(Profile::getId).collect(Collectors.toList());
        
    }

}
