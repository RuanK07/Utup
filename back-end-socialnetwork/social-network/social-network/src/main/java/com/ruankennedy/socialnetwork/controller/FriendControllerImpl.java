package com.ruankennedy.socialnetwork.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruankennedy.socialnetwork.dto.response.FriendDTO;
import com.ruankennedy.socialnetwork.model.Friend;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.service.serviceAction.FriendService;

@RestController
@RequestMapping(value = "/friends")
@Primary
public class FriendControllerImpl implements FriendController {

    private final FriendService friendService;

    public FriendControllerImpl(FriendService friendService) {
        this.friendService = friendService;
    }

    @Override
    @PostMapping(value = "/create/friend")
    public ResponseEntity<FriendDTO> createFriend(User userLogged, String nickname) {
        Friend friend = friendService.createFriend(userLogged, nickname);
        FriendDTO friendDTO = new FriendDTO(friend);
        return ResponseEntity.status(HttpStatus.CREATED).body(friendDTO);
    }

    @Override
    @GetMapping("/allfriends")
    public ResponseEntity<List<FriendDTO>> getFriendsByUserNickname(String nickname, User userLogged) {
        List<Friend> friends = friendService.getFriendsByUserNickname(nickname);
        List<FriendDTO> response = friends.stream()
                .map(FriendDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    
}