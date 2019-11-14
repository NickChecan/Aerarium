package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @Column(name = "ba_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "ba_name")
    private String name;

    @Column(name = "ba_description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "bank", targetEntity = AccountsPayable.class, fetch = FetchType.LAZY)
    private List<AccountsPayable> accountsPayable;

    @JsonIgnore
    @OneToMany(mappedBy = "bank", targetEntity = AccountsReceivable.class, fetch = FetchType.LAZY)
    private List<AccountsReceivable> accountsReceivable;

}
