package com.ruankennedy.socialnetwork.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "tb_friends")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Friend {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID", nullable = false, unique = true)
    private String id = UUID.randomUUID().toString();

    @Column(name = "FRIEND_START", nullable = false)
    @CreationTimestamp
    private LocalDateTime friendStart;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
    
    @Builder
    public Friend(LocalDateTime friendStart, Profile profile) {
    	super();
    	this.friendStart = friendStart;
        this.profile = profile;
    }
}