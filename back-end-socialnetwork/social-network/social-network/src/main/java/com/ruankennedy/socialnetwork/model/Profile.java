package com.ruankennedy.socialnetwork.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "PROFILE_PHOTO")
    private byte[] profilePhoto;

    @Column(name = "BIOGRAPHY")
    private String biography;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_profiles_posts",
    joinColumns = @JoinColumn(name = "profiles_id"),
    inverseJoinColumns = @JoinColumn(name = "posts_id"))
    private final List<Post> posts = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_profiles_friends",
    joinColumns = @JoinColumn(name = "profiles_id"),
    inverseJoinColumns = @JoinColumn(name = "friends_id"))
    private final List<Friend> friends = new ArrayList<>();

    @Builder
	private Profile(byte[] profilePhoto, String biography, User user) {
		super();
		this.profilePhoto = profilePhoto;
		this.biography = biography;
		this.user = user;
    }
    
    public void addPost(Post post) {
    	posts.add(post);
        post.setProfile(this);
    }

    public void removePost(Post post) {
    	posts.remove(post);
        post.setProfile(null);
    }
    
    public void addFriend(Friend friend) {
    	friends.add(friend);
        friend.setProfile(this);
    }

    public void removeFriend(Friend friend) {
    	friends.remove(friend);
        friend.setProfile(null);
    }

    public void addUser(User user) {
    	this.user = user;
    	user.setProfile(this);
    }
    
}