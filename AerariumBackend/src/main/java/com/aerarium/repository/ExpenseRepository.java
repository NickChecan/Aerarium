package com.aerarium.repository;

import com.aerarium.model.Company;
import com.aerarium.model.Expense;
import com.aerarium.projection.ExpenseProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(excerptProjection = ExpenseProjection.class)
public interface ExpenseRepository extends PagingAndSortingRepository<Expense, Long> {

    Page<Expense> findAll(Pageable pageable);

    Page<Expense> findAllByCompany(Company company, Pageable pageable);

    Page<Expense> findAllByCompanyName(String companyName, Pageable pageable);

    Page<Expense> findAllByDateBetween(Date fromDate, Date toDate, Pageable pageable);

    Page<Expense> findAllByDateBetweenAndCompanyName(
            Date fromDate, Date toDate, String companyName, Pageable pageable);

}
