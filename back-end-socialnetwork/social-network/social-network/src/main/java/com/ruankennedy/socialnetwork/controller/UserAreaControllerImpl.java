package com.ruankennedy.socialnetwork.controller;


import com.ruankennedy.socialnetwork.dto.request.ChangePassword;
import com.ruankennedy.socialnetwork.dto.response.UserDTO;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.service.serviceAction.UserAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/userarea")
@Primary 
public class UserAreaControllerImpl implements UserAreaController {

    private final UserAreaService userAreaService;  

    @Override
    @GetMapping(value = "/myprofile")
    public ResponseEntity<UserDTO> myProfile(User userLogged) {  
        return ResponseEntity.ok().body(userAreaService.myProfile(userLogged));
    }

    @Override
    @PutMapping(value = "/changepassword") 
    public ResponseEntity<String> changePassword(ChangePassword cpDTO, User userLogged) {  
        return ResponseEntity.ok().body(userAreaService.changePassword(cpDTO, userLogged));
    }

}
