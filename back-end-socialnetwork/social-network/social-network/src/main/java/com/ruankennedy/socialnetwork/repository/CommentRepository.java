package com.ruankennedy.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruankennedy.socialnetwork.model.Comment;
import com.ruankennedy.socialnetwork.model.Post;

public interface CommentRepository extends JpaRepository<Comment, String>{

	List<Comment> findByPost(Post post);

}
