package com.ruankennedy.socialnetwork.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
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
    private String id = UUID.randomUUID().toString();

    @Column(name = "SUBTITLE", nullable = false)
    private String subtitle;
    
    @Column(name = "POST_PHOTO",nullable = false, columnDefinition = "bytea[]")
    private List<byte[]> postPhoto;

    @Column(name = "POSTED_MOMENT", nullable = false)
    @CreationTimestamp
    private LocalDateTime postedMoment;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    
    @Builder
	private Post(String subtitle, List<byte[]> postPhoto, LocalDateTime postedMoment, Profile profile) {
		super();
		this.subtitle = subtitle;
		this.postPhoto = postPhoto;
		this.postedMoment = postedMoment;
		this.profile = profile;
	}
    
    
}
