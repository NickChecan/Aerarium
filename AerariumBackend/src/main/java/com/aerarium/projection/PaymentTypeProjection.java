package com.aerarium.projection;

import com.aerarium.model.PaymentType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "paymentType", types = { PaymentType.class })
public interface PaymentTypeProjection {

    Long getId();

    String getName();

    String getDescription();

}
