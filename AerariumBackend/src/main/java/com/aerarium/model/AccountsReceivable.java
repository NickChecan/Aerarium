package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "accounts_receivable")
public class AccountsReceivable {

    @Id
    @Column(name = "ar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "ar_dueDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dueDate;

    @Column(name = "ar_receiving_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date receivingDate;

    @Column(name = "ar_customer")
    private String customer;

    @Column(name = "ar_document")
    private String document;

    @Column(name = "ar_description")
    private String description;

    @Column(name = "ar_value")
    private Double value;

    @Column(name = "ar_destination")
    private String destination;

    @Column(name = "ar_comment")
    private String comment;

    @Column(name = "ar_net_value")
    private Double netValue;

    @Column(name = "ar_rate")
    private Double rate;

    @ManyToOne
    @JoinColumn(name = "ar_bs_id")
    private BillStatus billStatus;

    @ManyToOne
    @JoinColumn(name = "ar_co_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "ar_ba_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "ar_pt_id")
    private PaymentType paymentType;

}
