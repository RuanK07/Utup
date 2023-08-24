package com.ruankennedy.socialnetwork.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruankennedy.socialnetwork.dto.request.ChangeComment;
import com.ruankennedy.socialnetwork.dto.request.CreateCommentDTO;
import com.ruankennedy.socialnetwork.dto.response.CommentDTO;
import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.User;
import com.ruankennedy.socialnetwork.service.serviceAction.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentControllerImpl implements CommentController {

    private final CommentService commentService;

    public CommentControllerImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @PostMapping("/create/comment")
    public ResponseEntity<CommentDTO> createComment(String postId, CreateCommentDTO request, User userLogged) {
        Comment createdComment = commentService.createComment(userLogged, postId, request);
        CommentDTO commentDTO = new CommentDTO(createdComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDTO);
    }

    @Override
    @GetMapping("/post")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(String postId, User userLogged) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        List<CommentDTO> response = comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/edit")
    public ResponseEntity<CommentDTO> updateComment(String commentId, ChangeComment request, User userLogged) {
        Comment updatedComment = commentService.updateComment(commentId, request);
        CommentDTO response = new CommentDTO(updatedComment);
        return ResponseEntity.ok(response);
    }

}