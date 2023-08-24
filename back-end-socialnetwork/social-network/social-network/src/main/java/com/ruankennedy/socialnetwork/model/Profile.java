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
    private String id = UUID.randomUUID().toString();

    @Column(name = "PROFILE_PHOTO")
    private byte[] profilePhoto;

    @Column(name = "BIOGRAPHY")
    private String biography;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
    private final List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
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

    public void setPosts(List<Post> posts) {
        this.posts.clear();
        if (posts != null) {
            this.posts.addAll(posts);
            posts.forEach(post -> post.setProfile(this));
        }
    }
    
    public String getNickname() {
        return user != null ? user.getNickname() : null;
    }
    
}