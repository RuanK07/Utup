package com.ruankennedy.socialnetwork.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruankennedy.socialnetwork.model.Post;
import com.ruankennedy.socialnetwork.model.Profile;
import com.ruankennedy.socialnetwork.model.User;

public interface PostRepository extends JpaRepository<Post, String>{
	
	Optional<Post> findBySubtitle(String subtitle);

	List<Post> findBySubtitleContainingIgnoreCase(String subtitle);

	List<Post> findByProfile(Profile profile);

	List<Post> findByProfileUser(User userLogged);

	Post findByProfileUserAndSubtitleAndPostedMoment(User user, String postSubtitle, LocalDateTime postedMoment);

}
