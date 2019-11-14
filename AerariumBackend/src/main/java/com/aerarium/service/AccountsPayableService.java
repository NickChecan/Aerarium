package com.aerarium.service;

import com.aerarium.model.AccountsPayable;
import com.aerarium.model.Company;
import com.aerarium.repository.AccountsPayableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountsPayableService {

    private final AccountsPayableRepository accountsPayableRepository;

    private final SecurityService securityService;

    @Autowired
    public AccountsPayableService(
            AccountsPayableRepository accountsPayableRepository,
            SecurityService securityService) {
        this.accountsPayableRepository = accountsPayableRepository;
        this.securityService = securityService;
    }

    public Page<AccountsPayable> get(Date fromDate, Date toDate, String companyName, Pageable pageable) {

        if (!this.securityService.isAdmin()) { // Filtered by company for common users

            Company company = this.securityService.getMe().getCompany();
            return (fromDate != null && toDate != null)
                    ? this.accountsPayableRepository.findAllByDueDateBetweenAndCompanyName(
                            fromDate, toDate, company.getName(), pageable)
                    : this.accountsPayableRepository.findAllByCompany(company, pageable);

        } else { // Admin privileges

            // Period and company as filter
            if (fromDate != null && toDate != null && companyName != null)
                return this.accountsPayableRepository.findAllByDueDateBetweenAndCompanyName(
                        fromDate, toDate, companyName, pageable);

            // Using only company as filter
            if ((fromDate == null || toDate == null) && companyName != null)
                return this.accountsPayableRepository.findAllByCompanyName(companyName, pageable);

            // Filter only between a specific period
            if (fromDate != null && toDate != null)
                return this.accountsPayableRepository.findAllByDueDateBetween(fromDate, toDate, pageable);

            return this.accountsPayableRepository.findAll(pageable);
        }
    }

}
