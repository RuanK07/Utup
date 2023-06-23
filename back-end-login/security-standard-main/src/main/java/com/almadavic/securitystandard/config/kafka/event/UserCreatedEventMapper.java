package com.almadavic.securitystandard.config.kafka.event;

import com.almadavic.securitystandard.dto.response.UserMonitoringDTO;

public class UserCreatedEventMapper {

    public UserCreatedEvent mapToUserCreatedEvent(UserMonitoringDTO userMonitoringDTO) {
        UserCreatedEvent event = new UserCreatedEvent();
        event.setUserId(userMonitoringDTO.getId());
        event.setNickname(userMonitoringDTO.getNickname());
        event.setEmail(userMonitoringDTO.getEmail());
        return event;
    }
}