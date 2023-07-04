package com.ruankennedy.socialnetwork.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.repository.CommentRepository;
import com.ruankennedy.socialnetwork.repository.PostRepository;
import com.ruankennedy.socialnetwork.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class StartProjectConfigurations implements CommandLineRunner {
	
 private final ProfileRepository profileRepository;

 private final PostRepository postRepository;
 
 private final CommentRepository commentRepository;

 @Override
 public void run(String... args) {

    Profile p1 = new Profile();
    p1.setProfilePhoto(new byte[0]);
    p1.setBiography("Profile 1");

    Profile p2 = new Profile();
    p2.setProfilePhoto(new byte[0]);
    p2.setBiography("Profile 2");

    List<Profile> profiles = profileRepository.saveAll(Arrays.asList(p1, p2));

    Post post1 = new Post();
    post1.setSubtitle("Post 1");

    Post post2 = new Post();
    post2.setSubtitle("Post 2");

    List<Post> posts = postRepository.saveAll(Arrays.asList(post1, post2));

    Comment comment1 = new Comment();
    comment1.setComment("Comment 1");
    comment1.setPost(post1);

    Comment comment2 = new Comment();
    comment2.setComment("Comment 2");
    comment2.setPost(post2);

    List<Comment> comments = commentRepository.saveAll(Arrays.asList(comment1, comment2));

    p1.getPosts().addAll(posts);
    p2.getPosts().add(posts.get(1));

    post1.getComments().add(comment1);
    post2.getComments().add(comment2);

    profiles = profileRepository.saveAll(profiles);
    posts = postRepository.saveAll(posts);

    System.out.println("Profiles: " + profiles);
    System.out.println("Posts: " + posts);
    System.out.println("Comments: " + comments);
 }
}
