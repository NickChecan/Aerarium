package com.aerarium.repository;

import com.aerarium.model.Specification;
import com.aerarium.projection.SpecificationProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = SpecificationProjection.class)
public interface SpecificationRepository extends PagingAndSortingRepository<Specification, Long> {

}
