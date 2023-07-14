package com.ruankennedy.socialnetwork.dto.response;


import com.ruankennedy.socialnetwork.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;


@JsonPropertyOrder(value = {"roleName"})
@Getter
public class RoleDTO {

    @JsonProperty(value = "roleName")
    private final String roleName;

    public RoleDTO(Role role) {
        this.roleName = role.getAuthority();
    }

    @Override
    public String toString() {
        return roleName;
    }

}
