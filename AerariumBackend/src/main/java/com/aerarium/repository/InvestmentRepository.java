package com.aerarium.repository;

import com.aerarium.model.Company;
import com.aerarium.model.Investment;
import com.aerarium.projection.InvestmentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(excerptProjection = InvestmentProjection.class)
public interface InvestmentRepository extends PagingAndSortingRepository<Investment, Long> {

    Page<Investment> findAll(Pageable pageable);

    Page<Investment> findAllByCompany(Company company, Pageable pageable);

    Page<Investment> findAllByCompanyName(String companyName, Pageable pageable);

    Page<Investment> findAllByDateBetween(Date fromDate, Date toDate, Pageable pageable);

    Page<Investment> findAllByDateBetweenAndCompanyName(
            Date fromDate, Date toDate, String companyName, Pageable pageable);

}
