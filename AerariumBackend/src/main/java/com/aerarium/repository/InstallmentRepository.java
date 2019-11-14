package com.aerarium.repository;

import com.aerarium.model.Installment;
import com.aerarium.projection.InstallmentProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = InstallmentProjection.class)
public interface InstallmentRepository extends PagingAndSortingRepository<Installment, Long> {

}
