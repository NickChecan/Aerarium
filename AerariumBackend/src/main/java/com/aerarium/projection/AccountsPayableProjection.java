package com.aerarium.projection;

import com.aerarium.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "accountsPayable", types = { AccountsPayable.class })
public interface AccountsPayableProjection {

    Long getId();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getDueDate();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getPaymentDate();

    String getVendor();

    String getDocument();

    String getDescription();

    Double getValue();

    Double getRate();

    Company getCompany();

    CostType getCostType();

    Specification getSpecification();

    Bank getBank();

    PaymentType getPaymentType();

}
