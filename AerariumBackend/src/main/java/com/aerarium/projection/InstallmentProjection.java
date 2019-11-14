package com.aerarium.projection;

import com.aerarium.model.Installment;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "installment", types = { Installment.class })
public interface InstallmentProjection {

    Long getId();

    String getName();

    String getDescription();

}
