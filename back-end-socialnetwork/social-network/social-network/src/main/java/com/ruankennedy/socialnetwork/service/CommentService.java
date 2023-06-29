package com.ruankennedy.socialnetwork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getCommentById(String commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElse(null);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment updateComment(String commentId, String newComment) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            return null;
        }

        Comment comment = commentOptional.get();
        comment.setComment(newComment);

        return commentRepository.save(comment);
    }

    public void deleteComment(String commentId) {
        // Verifique se o comentário existe antes de excluí-lo
        if (!commentRepository.existsById(commentId)) {
            throw new IllegalArgumentException("Comment not found with id: " + commentId);
        }

        commentRepository.deleteById(commentId);
    }
}