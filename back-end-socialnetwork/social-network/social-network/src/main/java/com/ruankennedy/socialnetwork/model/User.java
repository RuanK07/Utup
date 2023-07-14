package com.ruankennedy.socialnetwork.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import com.ruankennedy.socialnetwork.repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@Getter 
@Setter 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

    @Id    
    @EqualsAndHashCode.Include 
    @Column(name = "ID", nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();
    
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String nickname; 

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;     

    @Column(name = "PASSWORD", nullable = false)
    @Getter(AccessLevel.NONE)
    private String password; 

    @Column(name = "REGISTRATION_MOMENT", nullable = false)
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime registrationMoment;

    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter(AccessLevel.NONE) 
    private final List<Role> roles = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    
    @Builder
    public User(@NonNull String nickname, @NonNull String email, @NonNull String password, ProfileRepository profileRepository) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
    
    public void addProfile(Profile profile) {
    	this.profile = profile;
    	profile.setUser(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setUser(null);
    }


    // -------------------------------------------- MÉTODOS DEFAULT PELA INTERFACE USERDETAILS -----------------------

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public List<Role> getAuthorities() {
        return this.roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Id: " + id + ", Name: " + getNickname() + ", Email: " + email + "\n");
        sb.append("---Roles---\n");
        for (Role role : roles) {
            sb.append(role + "\n");
        }
        sb.append("---Roles---\n");
        sb.append("\n");
        return sb.toString();

    }

}
