package com.aerarium.projection;

import com.aerarium.model.BillStatus;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "billStatus", types = { BillStatus.class })
public interface BillStatusProjection {

    Long getId();

    String getName();

    String getDescription();

}
