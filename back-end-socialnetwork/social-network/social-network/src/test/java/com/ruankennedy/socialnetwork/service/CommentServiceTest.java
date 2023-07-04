package com.ruankennedy.socialnetwork.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.repository.CommentRepository;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateComment() {
        Comment comment = createSampleComment();
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment createdComment = commentService.createComment(comment);

        assertNotNull(createdComment);
        assertEquals(comment.getId(), createdComment.getId());
        assertEquals(comment.getComment(), createdComment.getComment());
        assertEquals(comment.getCommentedMoment(), createdComment.getCommentedMoment());
        assertEquals(comment.getPost(), createdComment.getPost());

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testGetCommentById() {
        String commentId = UUID.randomUUID().toString();
        Comment comment = createSampleComment();
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        Comment retrievedComment = commentService.getCommentById(commentId);

        assertNotNull(retrievedComment);
        assertEquals(comment.getId(), retrievedComment.getId());
        assertEquals(comment.getComment(), retrievedComment.getComment());
        assertEquals(comment.getCommentedMoment(), retrievedComment.getCommentedMoment());
        assertEquals(comment.getPost(), retrievedComment.getPost());

        verify(commentRepository, times(1)).findById(commentId);
    }

    @Test
    void testGetAllComments() {
        List<Comment> comments = createSampleCommentsList();
        when(commentRepository.findAll()).thenReturn(comments);

        List<Comment> retrievedComments = commentService.getAllComments();

        assertEquals(comments.size(), retrievedComments.size());
        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            Comment retrievedComment = retrievedComments.get(i);
            assertEquals(comment.getId(), retrievedComment.getId());
            assertEquals(comment.getComment(), retrievedComment.getComment());
            assertEquals(comment.getCommentedMoment(), retrievedComment.getCommentedMoment());
            assertEquals(comment.getPost(), retrievedComment.getPost());
        }

        verify(commentRepository, times(1)).findAll();
    }

    @Test
    void testUpdateComment() {
        String commentId = UUID.randomUUID().toString();
        String newCommentText = "New comment";
        Comment comment = createSampleComment();
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        Comment updatedComment = commentService.updateComment(commentId, newCommentText);

        assertNotNull(updatedComment);
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(newCommentText, updatedComment.getComment());
        assertEquals(comment.getCommentedMoment(), updatedComment.getCommentedMoment());
        assertEquals(comment.getPost(), updatedComment.getPost());

        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void testDeleteComment() {
        String commentId = UUID.randomUUID().toString();
        when(commentRepository.existsById(commentId)).thenReturn(true);

        commentService.deleteComment(commentId);

        verify(commentRepository, times(1)).existsById(commentId);
        verify(commentRepository, times(1)).deleteById(commentId);
    }

    private Comment createSampleComment() {
        Comment comment = new Comment();
        comment.setComment("Sample comment");
        comment.setCommentedMoment(LocalDateTime.now());
        comment.setPost(new Post());
        return comment;
    }

    private List<Comment> createSampleCommentsList() {
        List<Comment> comments = new ArrayList<>();
        comments.add(createSampleComment());
        comments.add(createSampleComment());
        return comments;
    }
}