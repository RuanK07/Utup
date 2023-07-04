package com.ruankennedy.socialnetwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruankennedy.socialnetwork.model.Post;

public interface PostRepository extends JpaRepository<Post, String>{
	
	Optional<Post> findBySubtitle(String subtitle);
	
}
