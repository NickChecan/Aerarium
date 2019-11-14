package com.aerarium.repository;

import com.aerarium.model.AccountsReceivable;
import com.aerarium.model.Company;
import com.aerarium.projection.AccountsReceivableProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(excerptProjection = AccountsReceivableProjection.class)
public interface AccountsReceivableRepository extends PagingAndSortingRepository<AccountsReceivable, Long> {

    Page<AccountsReceivable> findAll(Pageable pageable);

    Page<AccountsReceivable> findAllByCompany(Company company, Pageable pageable);

    Page<AccountsReceivable> findAllByCompanyName(String companyName, Pageable pageable);

    Page<AccountsReceivable> findAllByDueDateBetween(Date fromDate, Date toDate, Pageable pageable);

    Page<AccountsReceivable> findAllByDueDateBetweenAndCompanyName(
            Date fromDate, Date toDate, String companyName, Pageable pageable);

}
