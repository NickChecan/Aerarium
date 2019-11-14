package com.aerarium.repository;

import com.aerarium.model.AccountsPayable;
import com.aerarium.model.Company;
import com.aerarium.projection.AccountsPayableProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(excerptProjection = AccountsPayableProjection.class)
public interface AccountsPayableRepository extends PagingAndSortingRepository<AccountsPayable, Long> {

    Page<AccountsPayable> findAll(Pageable pageable);

    Page<AccountsPayable> findAllByCompany(Company company, Pageable pageable);

    Page<AccountsPayable> findAllByCompanyName(String companyName, Pageable pageable);

    Page<AccountsPayable> findAllByDueDateBetween(Date fromDate, Date toDate, Pageable pageable);

    Page<AccountsPayable> findAllByDueDateBetweenAndCompanyName(
            Date fromDate, Date toDate, String companyName, Pageable pageable);

}
