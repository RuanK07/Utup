package com.ruankennedy.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ruankennedy.socialnetwork.model.Friend;
import com.ruankennedy.socialnetwork.model.Profile;

@Repository
public interface FriendRepository extends JpaRepository<Friend, String> {
    List<Friend> findByProfileId(String profileId);

    void deleteByProfileAndTargetProfile(Profile profile, Profile targetProfile);
}