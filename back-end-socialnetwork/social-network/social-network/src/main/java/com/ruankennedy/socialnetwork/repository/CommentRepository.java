package com.ruankennedy.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruankennedy.socialnetwork.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>{

}
