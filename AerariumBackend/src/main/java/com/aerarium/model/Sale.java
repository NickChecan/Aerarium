package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @Column(name = "sa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "sa_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @Column(name = "sa_vendor")
    private String vendor;

    @Column(name = "sa_customer")
    private String customer;

    @Column(name = "sa_purchase_value")
    private Double purchaseValue;

    @Column(name = "sa_installment_value")
    private Double installmentValue;

    @Column(name = "sa_budget_number")
    private Double budgetNumber;

    @Column(name = "sa_architect")
    private String architect;

    @Column(name = "sa_commission")
    private Double commission;

    @Column(name = "sa_comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "sa_co_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "sa_pt_id")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "sa_in_id")
    private Installment installment;

}
