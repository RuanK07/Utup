package com.ruankennedy.socialnetwork.service.serviceAction;

import java.util.List;

import com.ruankennedy.socialnetwork.dto.request.ChangePostSubtitle;
import com.ruankennedy.socialnetwork.dto.request.CreatePostDTO;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.User;


public interface PostService {
    Post createPost(User userLogged, CreatePostDTO request);
    Post getPostById(String postId);
    Post updatePostSubtitle(String postId, ChangePostSubtitle request);
	List<Post> getPostsByUserNickname(String nickname);
	List<Post> getPostsBySubtitle(String subtitle);
}