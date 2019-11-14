package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "payment_type")
public class PaymentType {

    @Id
    @Column(name = "pt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "pt_name")
    private String name;

    @Column(name = "pt_description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentType", targetEntity = Sale.class, fetch = FetchType.LAZY)
    private List<Sale> sales;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentType", targetEntity = AccountsPayable.class, fetch = FetchType.LAZY)
    private List<AccountsPayable> accountsPayable;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentType", targetEntity = AccountsReceivable.class, fetch = FetchType.LAZY)
    private List<AccountsPayable> accountsReceivable;

}
