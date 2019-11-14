package com.aerarium.projection;

import com.aerarium.model.CostType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "costType", types = { CostType.class })
public interface CostTypeProjection {

    Long getId();

    String getName();

    String getDescription();

}
