package com.ruankennedy.socialnetwork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(String postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(String postId, String newSubtitle) {
        Optional<Post> existingPostOptional = postRepository.findById(postId);
        if (existingPostOptional.isEmpty()) {
            return null;
        }

        Post existingPost = existingPostOptional.get();
        existingPost.setSubtitle(newSubtitle);

        return postRepository.save(existingPost);
    }

    public boolean deletePost(String postId) {
        if (!postRepository.existsById(postId)) {
            return false;
        }
        postRepository.deleteById(postId);
        return true;
    }
}