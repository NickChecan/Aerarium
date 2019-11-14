package com.aerarium.service;

import com.aerarium.model.Company;
import com.aerarium.model.Expense;
import com.aerarium.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final SecurityService securityService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, SecurityService securityService) {
        this.expenseRepository = expenseRepository;
        this.securityService = securityService;
    }

    public Page<Expense> get(Date fromDate, Date toDate, String companyName, Pageable pageable) {

        if (!this.securityService.isAdmin()) { // Filtered by company for common users

            Company company = this.securityService.getMe().getCompany();
            return (fromDate != null && toDate != null)
                    ? this.expenseRepository.findAllByDateBetweenAndCompanyName(
                            fromDate, toDate, company.getName(), pageable)
                    : this.expenseRepository.findAllByCompany(company, pageable);

        } else { // Admin privileges

            // Period and company as filter
            if (fromDate != null && toDate != null && companyName != null)
                return this.expenseRepository.findAllByDateBetweenAndCompanyName(fromDate, toDate, companyName, pageable);

            // Using only company as filter
            if ((fromDate == null || toDate == null) && companyName != null)
                return this.expenseRepository.findAllByCompanyName(companyName, pageable);

            // Filter only between a specific period
            if (fromDate != null && toDate != null)
                return this.expenseRepository.findAllByDateBetween(fromDate, toDate, pageable);

            return this.expenseRepository.findAll(pageable);
        }
    }

}
