package com.aerarium.projection;

import com.aerarium.model.Role;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "role", types = { Role.class })
public interface RoleProjection {

    Long getId();

    String getName();

    String getDescription();

}
