package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_account")
public class User {

    @Id
    @Column(name = "us_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "us_name")
    private String name;

    @Column(name = "us_email")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "us_password")
    private String password;

    @Column(name = "us_phone")
    private String phone;

    @Column(name = "us_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "us_co_id")
    private Company company;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "ur_us_id", referencedColumnName = "us_id"),
            inverseJoinColumns = @JoinColumn(name = "ur_ro_id", referencedColumnName = "ro_id")
    )
    private List<Role> roles;

    // Constructor for the security configuration
    public User(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
        this.setActive(user.isActive());
        this.setCompany(user.getCompany());
        this.setRoles(user.getRoles());
    }

}
