package com.ruankennedy.socialnetwork.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.repository.PostRepository;

public class PostServiceTest {
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postService = new PostService(postRepository);
    }

    @Test
    void testCreatePost() {
        Post post = createSamplePost();
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post createdPost = postService.createPost(post);

        Assertions.assertNotNull(createdPost);
        Assertions.assertEquals(post.getId(), createdPost.getId());
        Assertions.assertEquals(post.getSubtitle(), createdPost.getSubtitle());
        Assertions.assertEquals(post.getPostedMoment(), createdPost.getPostedMoment());
        Assertions.assertEquals(post.getComments().size(), createdPost.getComments().size());

        verify(postRepository, times(1)).save(post);
    }

    @Test
    void testGetPostById() {
        Post post = createSamplePost();
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));

        Post retrievedPost = postService.getPostById(post.getId());

        Assertions.assertNotNull(retrievedPost);
        Assertions.assertEquals(post.getId(), retrievedPost.getId());
        Assertions.assertEquals(post.getSubtitle(), retrievedPost.getSubtitle());
        Assertions.assertEquals(post.getPostedMoment(), retrievedPost.getPostedMoment());
        Assertions.assertEquals(post.getComments().size(), retrievedPost.getComments().size());

        verify(postRepository, times(1)).findById(post.getId());
    }

    @Test
    void testGetPostById_NotFound() {
        String postId = "nonExistentId";
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        Post retrievedPost = postService.getPostById(postId);

        Assertions.assertNull(retrievedPost);

        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    void testGetAllPosts() {
        List<Post> postList = createSamplePostList();
        when(postRepository.findAll()).thenReturn(postList);

        List<Post> retrievedPosts = postService.getAllPosts();

        Assertions.assertEquals(postList.size(), retrievedPosts.size());

        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testUpdatePostSubtitle() {
        String postId = "existingPostId";
        String newSubtitle = "Updated subtitle";

        Post existingPost = createSamplePost();
        existingPost.setId(postId);
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
        when(postRepository.save(any(Post.class))).thenReturn(existingPost);

        Post updatedPost = postService.updatePostSubtitle(postId, newSubtitle);

        Assertions.assertNotNull(updatedPost);
        Assertions.assertEquals(existingPost.getId(), updatedPost.getId());
        Assertions.assertEquals(newSubtitle, updatedPost.getSubtitle());

        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).save(existingPost);
    }

    @Test
    void testUpdatePostSubtitle_NotFound() {
        String postId = "nonExistentId";
        String newSubtitle = "Updated subtitle";

        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        Post updatedPost = postService.updatePostSubtitle(postId, newSubtitle);

        Assertions.assertNull(updatedPost);

        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, never()).save(any(Post.class));
    }

    @Test
    void testDeletePost() {
        // Arrange
        String postId = "existingPostId";
        Post existingPost = createSamplePost();
        existingPost.setId(postId);
        when(postRepository.existsById(postId)).thenReturn(true);

        boolean deletionResult = postService.deletePost(postId);

        Assertions.assertTrue(deletionResult);

        verify(postRepository, times(1)).existsById(postId);
        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    void testDeletePost_NotFound() {
        // Arrange
        String postId = "nonExistentId";
        when(postRepository.existsById(postId)).thenReturn(false);

        boolean deletionResult = postService.deletePost(postId);

        Assertions.assertFalse(deletionResult);

        verify(postRepository, times(1)).existsById(postId);
        verify(postRepository, never()).deleteById(any());
    }

    private Post createSamplePost() {
        Post post = new Post();
        post.setSubtitle("Sample post subtitle");
        post.setPostedMoment(LocalDateTime.now());

        Comment comment1 = new Comment();
        comment1.setComment("Comment 1");
        comment1.setCommentedMoment(LocalDateTime.now());
        comment1.setPost(post);
        post.getComments().add(comment1);

        Comment comment2 = new Comment();
        comment2.setComment("Comment 2");
        comment2.setCommentedMoment(LocalDateTime.now());
        comment2.setPost(post);
        post.getComments().add(comment2);

        return post;
    }

    private List<Post> createSamplePostList() {
        List<Post> postList = new ArrayList<>();
        postList.add(createSamplePost());
        postList.add(createSamplePost());
        return postList;
    }
}