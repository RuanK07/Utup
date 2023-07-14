package com.ruankennedy.socialnetwork.model;


import com.ruankennedy.socialnetwork.enumerated.RoleName;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "tb_roles")
@NoArgsConstructor
@Builder
@NonNull
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true) 
public class Role implements GrantedAuthority {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "ID", nullable = false, unique = true)
    private final String id = UUID.randomUUID().toString();
    @Column(name = "NAME", nullable = false, unique = true)
    @Getter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private final List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return name.toString();
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + getAuthority();
    }

}