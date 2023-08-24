package com.ruankennedy.socialnetwork.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ruankennedy.socialnetwork.model.Friend;

import lombok.Getter;
import lombok.Setter;


@JsonPropertyOrder(value = {"friendStart"})
@Getter
@Setter
public class FriendDTO {
    
    @JsonProperty("friendStart")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime friendStart;

    public FriendDTO(Friend friend) {
        this.friendStart = friend.getFriendStart();
    }

}