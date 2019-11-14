package com.aerarium.projection;

import com.aerarium.model.Specification;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "specification", types = { Specification.class })
public interface SpecificationProjection {

    Long getId();

    String getName();

    String getDescription();

}
