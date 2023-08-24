package com.ruankennedy.socialnetwork.service.serviceAction;

import java.util.List;

import com.ruankennedy.socialnetwork.model.Friend;
import com.ruankennedy.socialnetwork.model.User;

public interface FriendService {
    Friend createFriend(User userLogged, String nickname);
    List<Friend> getFriendsByUserNickname(String nickname);
}