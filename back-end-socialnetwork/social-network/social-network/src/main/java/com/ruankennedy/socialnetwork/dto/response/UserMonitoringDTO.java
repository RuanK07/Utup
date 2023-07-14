package com.ruankennedy.socialnetwork.dto.response;


import com.ruankennedy.socialnetwork.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ruankennedy.socialnetwork.util.UserMapper;

import lombok.Getter;

import java.util.List;


@JsonPropertyOrder(value = {"id", "nickname", "email", "roles", "registrationMoment"})
@Getter
public class UserMonitoringDTO extends UserDTO {

    @JsonProperty(value = "id") 
    private final String id;

    @JsonProperty(value = "roles")
    private final List<RoleDTO> rolesDTO;

    public UserMonitoringDTO(User user) {
        super(user);
        this.id = user.getId();
        this.rolesDTO = UserMapper.toRoleDTO(user.getAuthorities());
    }

}
