package com.ruankennedy.socialnetwork.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_profiles")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profile {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID", nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();

    @Column(name = "PROFILE_PHOTO", unique = true)
    private String profilePhoto;

    @Column(name = "BIOGRAPHY", unique = true)
    private String biography;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_profiles_posts",
    joinColumns = @JoinColumn(name = "profiles_id"),
    inverseJoinColumns = @JoinColumn(name = "posts_id"))
    private final List<Post> posts = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
	    name = "profiles_friends",
	    joinColumns = @JoinColumn(name = "profiles_id", referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name = "friends_id", referencedColumnName = "id")
	)
	private List<Profile> friends;

}