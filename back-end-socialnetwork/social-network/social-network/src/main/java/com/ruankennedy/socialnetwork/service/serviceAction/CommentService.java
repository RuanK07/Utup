package com.ruankennedy.socialnetwork.service.serviceAction;

import java.util.List;

import com.ruankennedy.socialnetwork.dto.request.ChangeComment;
import com.ruankennedy.socialnetwork.dto.request.CreateCommentDTO;
import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.User;

public interface CommentService {
    Comment createComment(User userLogged, String postId, CreateCommentDTO createCommentDTO);
    Comment getCommentById(String commentId);
    List<Comment> getCommentsByPostId(String postId);
    Comment updateComment(String commentId, ChangeComment request);
}