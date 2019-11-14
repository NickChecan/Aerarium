package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "bill_status")
public class BillStatus {

    @Id
    @Column(name = "bs_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "bs_name")
    private String name;

    @Column(name = "bs_description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "billStatus", targetEntity = AccountsReceivable.class, fetch = FetchType.LAZY)
    private List<AccountsReceivable> accountsReceivable;

}
