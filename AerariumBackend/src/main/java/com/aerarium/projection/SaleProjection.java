package com.aerarium.projection;

import com.aerarium.model.Company;
import com.aerarium.model.Installment;
import com.aerarium.model.PaymentType;
import com.aerarium.model.Sale;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "sale", types = { Sale.class })
public interface SaleProjection {

    Long getId();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getDate();

    String getVendor();

    String getCustomer();

    Double getPurchaseValue();

    Double getInstallmentValue();

    Double getBudgetNumber();

    String getArchitect();

    Double getCommission();

    String getComment();

    Company getCompany();

    PaymentType getPaymentType();

    Installment getInstallment();

}
