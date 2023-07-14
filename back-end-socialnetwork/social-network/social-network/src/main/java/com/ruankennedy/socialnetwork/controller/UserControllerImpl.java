package com.ruankennedy.socialnetwork.controller;


import com.ruankennedy.socialnetwork.dto.request.RegisterUserDTO;
import com.ruankennedy.socialnetwork.dto.response.UserMonitoringDTO;
import com.ruankennedy.socialnetwork.service.serviceAction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
@Primary 
public class UserControllerImpl implements UserController { 

    private final UserService userService; 

    @Override
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(RegisterUserDTO registerData, UriComponentsBuilder uriBuilder) { 

        UserMonitoringDTO userDTO = userService.register(registerData);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(userDTO.getId()).toUri(); 

        String message = userDTO.getNickname() + ", your account was registered successfully!";

        return ResponseEntity.created(uri).body(message);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<UserMonitoringDTO>> findAll(Pageable pageable, String roleName) {
        return ResponseEntity.ok().body(userService.findAll(pageable, roleName));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserMonitoringDTO> findById(String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

}