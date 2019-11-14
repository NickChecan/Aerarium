package com.aerarium.projection;

import com.aerarium.model.Company;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "company", types = { Company.class })
public interface CompanyProjection {

    Long getId();

    String getName();

}
