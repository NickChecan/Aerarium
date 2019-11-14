package com.aerarium.repository;

import com.aerarium.model.CostType;
import com.aerarium.projection.CostTypeProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = CostTypeProjection.class)
public interface CostTypeRepository extends PagingAndSortingRepository<CostType, Long> {

}
