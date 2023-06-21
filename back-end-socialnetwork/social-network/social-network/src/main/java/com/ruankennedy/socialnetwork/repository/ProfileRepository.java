package com.ruankennedy.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruankennedy.socialnetwork.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String>{

}
