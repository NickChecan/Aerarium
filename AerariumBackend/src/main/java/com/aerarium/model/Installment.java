package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "installment")
public class Installment {

    @Id
    @Column(name = "in_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "in_name")
    private String name;

    @Column(name = "in_description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "installment", targetEntity = Sale.class, fetch = FetchType.LAZY)
    private List<Sale> sales;

}
