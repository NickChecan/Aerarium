package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cost_type")
public class CostType {

    @Id
    @Column(name = "ct_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "ct_name")
    private String name;

    @Column(name = "ct_description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "costType", targetEntity = AccountsPayable.class, fetch = FetchType.LAZY)
    private List<AccountsPayable> accountsPayable;

}
