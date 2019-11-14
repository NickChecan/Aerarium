package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "co_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "co_name")
    private String name;

    //@JsonIgnore
    @OneToMany(mappedBy = "company", targetEntity = User.class, fetch = FetchType.LAZY)
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "company", targetEntity = Sale.class, fetch = FetchType.LAZY)
    private List<Sale> sales;

    @JsonIgnore
    @OneToMany(mappedBy = "company", targetEntity = AccountsPayable.class, fetch = FetchType.LAZY)
    private List<AccountsPayable> accountsPayable;

    @JsonIgnore
    @OneToMany(mappedBy = "company", targetEntity = AccountsReceivable.class, fetch = FetchType.LAZY)
    private List<AccountsReceivable> accountsReceivable;

    //@JsonIgnore
    @OneToMany(mappedBy = "company", targetEntity = Expense.class, fetch = FetchType.LAZY)
    private List<Expense> expenses;

    @JsonIgnore
    @OneToMany(mappedBy = "company", targetEntity = Investment.class, fetch = FetchType.LAZY)
    private List<Investment> investments;

}
