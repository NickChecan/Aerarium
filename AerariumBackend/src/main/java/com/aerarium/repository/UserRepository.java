package com.aerarium.repository;

import com.aerarium.model.User;
import com.aerarium.projection.UserProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

}
