package com.aerarium.repository;

import com.aerarium.model.Company;
import com.aerarium.model.Sale;
import com.aerarium.projection.SaleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(excerptProjection = SaleProjection.class)
public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {

    Page<Sale> findAll(Pageable pageable);

    Page<Sale> findAllByCompany(Company company, Pageable pageable);

    Page<Sale> findAllByCompanyName(String companyName, Pageable pageable);

    Page<Sale> findAllByDateBetween(Date fromDate, Date toDate, Pageable pageable);

    Page<Sale> findAllByDateBetweenAndCompanyName(
            Date fromDate, Date toDate, String companyName, Pageable pageable);

}
