package com.ruankennedy.socialnetwork.repository;


import com.ruankennedy.socialnetwork.model.Role;
import com.ruankennedy.socialnetwork.enumerated.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(RoleName name);

}
