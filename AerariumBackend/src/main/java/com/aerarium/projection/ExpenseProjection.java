package com.aerarium.projection;

import com.aerarium.model.Company;
import com.aerarium.model.Expense;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "expense", types = { Expense.class })
public interface ExpenseProjection {

    String getName();

    Long getId();

    Company getCompany();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date getDate();

    Double getValue();

    String getDescription();

}
