package com.aerarium.repository;

import com.aerarium.model.Role;
import com.aerarium.projection.RoleProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = RoleProjection.class)
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Optional<Role> findByName(String name);

    // Prevents POST /roles and PATCH /roles/:id
    @Override
    @RestResource(exported = false)
    public Role save(Role role);

    // Prevents DELETE /roles/:id
    @Override
    @RestResource(exported = false)
    public void delete(Role role);

}
