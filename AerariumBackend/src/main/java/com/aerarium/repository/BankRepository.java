package com.aerarium.repository;

import com.aerarium.model.Bank;
import com.aerarium.projection.BankProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = BankProjection.class)
public interface BankRepository extends PagingAndSortingRepository<Bank, Long> {

}
