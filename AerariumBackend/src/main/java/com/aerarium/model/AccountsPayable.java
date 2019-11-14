package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "accounts_payable")
public class AccountsPayable {

    @Id
    @Column(name = "ap_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "ap_dueDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dueDate;

    @Column(name = "ap_paymentDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date paymentDate;

    @Column(name = "ap_vendor")
    private String vendor;

    @Column(name = "ap_document")
    private String document;

    @Column(name = "ap_description")
    private String description;

    @Column(name = "ap_value")
    private Double value;

    @Column(name = "ap_rate")
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "ap_co_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "ap_ct_id")
    private CostType costType;

    @ManyToOne
    @JoinColumn(name = "ap_sp_id")
    private Specification specification;

    @ManyToOne
    @JoinColumn(name = "ap_ba_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "ap_pt_id")
    private PaymentType paymentType;

}
