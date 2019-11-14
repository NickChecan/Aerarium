package com.aerarium.projection;

import com.aerarium.model.Company;
import com.aerarium.model.Investment;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "investment", types = { Investment.class })
public interface InvestmentProjection {

    Long getId();

    Company getCompany();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getDate();

    Double getValue();

    String getName();

    String getDescription();

}
