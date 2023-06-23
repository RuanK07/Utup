package com.ruankennedy.socialnetwork.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_posts")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
	@Id
    @EqualsAndHashCode.Include
    @Column(name = "ID", nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();

    @Column(name = "SUBTITLE", nullable = false, unique = true)
    private String subtitle;
    
    @ElementCollection
    @Column(name = "POST_PHOTO",nullable = false, columnDefinition = "bytea")
    private List<byte[]> postPhoto;

    @Column(name = "POSTED_MOMENT", nullable = false)
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime postedMoment;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_posts_comments",
    joinColumns = @JoinColumn(name = "posts_id"),
    inverseJoinColumns = @JoinColumn(name = "comments_id"))
    private final List<Comment> comments = new ArrayList<>();
    

}
