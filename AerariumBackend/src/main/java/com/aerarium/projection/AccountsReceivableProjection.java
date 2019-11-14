package com.aerarium.projection;

import com.aerarium.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "accountsReceivable", types = { AccountsReceivable.class })
public interface AccountsReceivableProjection {

    Long getId();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getDueDate();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getReceivingDate();

    String getCustomer();

    String getDocument();

    String getDescription();

    Double getValue();

    String getDestination();

    String getComment();

    Double getNetValue();

    Double getRate();

    BillStatus getBillStatus();

    Company getCompany();

    Bank getBank();

    PaymentType getPaymentType();

}
