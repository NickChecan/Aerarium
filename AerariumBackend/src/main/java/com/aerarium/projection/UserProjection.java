package com.aerarium.projection;

import com.aerarium.model.Company;
import com.aerarium.model.Role;
import com.aerarium.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "user", types = { User.class })
public interface UserProjection {

    Long getId();

    String getName();

    String getEmail();

    String getPhone();

    boolean isActive();

    Company getCompany();

    List<Role> getRoles();

}
