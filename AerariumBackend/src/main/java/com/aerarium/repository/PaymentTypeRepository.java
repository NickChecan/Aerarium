package com.aerarium.repository;

import com.aerarium.model.PaymentType;
import com.aerarium.projection.PaymentTypeProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PaymentTypeProjection.class)
public interface PaymentTypeRepository extends PagingAndSortingRepository<PaymentType, Long> {

}
