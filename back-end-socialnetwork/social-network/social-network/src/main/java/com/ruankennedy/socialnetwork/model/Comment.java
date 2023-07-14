package com.ruankennedy.socialnetwork.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_comments")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment {

	@Id
    @EqualsAndHashCode.Include
    @Column(name = "ID", nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();

    @Column(name = "COMMENT", nullable = false, unique = true)
    private String comment;
    
    @Column(name = "COMMENTED_MOMENT", nullable = false)
    @CreationTimestamp
    private LocalDateTime commentedMoment;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    private Post post;
    
    @Builder
    public Comment(String comment, LocalDateTime commentedMoment, User user, Post post) {
    	super();
    	this.comment = comment;
        this.commentedMoment = commentedMoment;
        this.user = user;
        this.post = post;
    }
    
}
