package com.aerarium.projection;

import com.aerarium.model.Bank;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "bank", types = { Bank.class })
public interface BankProjection {

    Long getId();

    String getName();

    String getDescription();

}
