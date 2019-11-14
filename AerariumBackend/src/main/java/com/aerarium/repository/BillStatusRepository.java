package com.aerarium.repository;

import com.aerarium.model.BillStatus;
import com.aerarium.projection.BillStatusProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = BillStatusProjection.class)
public interface BillStatusRepository extends PagingAndSortingRepository<BillStatus, Long> {

}
