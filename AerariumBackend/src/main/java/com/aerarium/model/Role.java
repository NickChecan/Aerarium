package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "application_role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "ro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "ro_name")
    private String name;

    @Column(name = "ro_description")
    private String description;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.getName();
    }

    public Role(String name) {
        this.name = name;
    }

}
