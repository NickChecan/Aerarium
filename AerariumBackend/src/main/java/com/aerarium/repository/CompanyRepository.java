package com.aerarium.repository;

import com.aerarium.model.Company;
import com.aerarium.projection.CompanyProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = CompanyProjection.class)
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

    Optional<Company> findByName(String name);

}
