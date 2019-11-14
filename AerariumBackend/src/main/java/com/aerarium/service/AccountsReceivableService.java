package com.aerarium.service;

import com.aerarium.model.AccountsReceivable;
import com.aerarium.model.Company;
import com.aerarium.repository.AccountsReceivableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountsReceivableService {

    private final AccountsReceivableRepository accountsReceivableRepository;

    private final SecurityService securityService;

    @Autowired
    public AccountsReceivableService(
            AccountsReceivableRepository accountsReceivableRepository,
            SecurityService securityService) {
        this.accountsReceivableRepository = accountsReceivableRepository;
        this.securityService = securityService;
    }

    public Page<AccountsReceivable> get(Date fromDate, Date toDate, String companyName, Pageable pageable) {

        if (!this.securityService.isAdmin()) { // Filtered by company for common users

            Company company = this.securityService.getMe().getCompany();
            return (fromDate != null && toDate != null)
                    ? this.accountsReceivableRepository.findAllByDueDateBetweenAndCompanyName(
                            fromDate, toDate, company.getName(), pageable)
                    : this.accountsReceivableRepository.findAllByCompany(company, pageable);

        } else { // Admin privileges

            // Period and company as filter
            if (fromDate != null && toDate != null && companyName != null)
                return this.accountsReceivableRepository.findAllByDueDateBetweenAndCompanyName(
                        fromDate, toDate, companyName, pageable);

            // Using only company as filter
            if ((fromDate == null || toDate == null) && companyName != null)
                return this.accountsReceivableRepository.findAllByCompanyName(companyName, pageable);

            // Filter only between a specific period
            if (fromDate != null && toDate != null)
                return this.accountsReceivableRepository.findAllByDueDateBetween(fromDate, toDate, pageable);

            return this.accountsReceivableRepository.findAll(pageable);
        }
    }

}
