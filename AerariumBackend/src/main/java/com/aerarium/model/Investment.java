package com.aerarium.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "investment")
public class Investment {

    @Id
    @Column(name = "iv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "iv_co_id")
    private Company company;

    @Column(name = "iv_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @Column(name = "iv_value")
    private Double value;

    @Column(name = "iv_name")
    private String name;

    @Column(name = "iv_description")
    private String description;

}
