package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "specification")
public class Specification {

    @Id
    @Column(name = "sp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "sp_name")
    private String name;

    @Column(name = "sp_description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "specification", targetEntity = AccountsPayable.class, fetch = FetchType.LAZY)
    private List<AccountsPayable> accountsPayable;

}
