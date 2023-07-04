package com.ruankennedy.socialnetwork.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    
    @ManyToOne
    private Post post;
    
}
