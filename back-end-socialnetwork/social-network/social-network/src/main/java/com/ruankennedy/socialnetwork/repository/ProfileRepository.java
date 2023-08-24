package com.ruankennedy.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ruankennedy.socialnetwork.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String>{
	
	Profile findByUserId(String id);
	List<Profile> findByUserIdIn(List<String> userIds);
}
