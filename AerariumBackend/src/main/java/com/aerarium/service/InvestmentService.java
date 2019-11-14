package com.aerarium.service;

import com.aerarium.model.Company;
import com.aerarium.model.Investment;
import com.aerarium.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    private final SecurityService securityService;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository, SecurityService securityService) {
        this.investmentRepository = investmentRepository;
        this.securityService = securityService;
    }

    public Page<Investment> get(Date fromDate, Date toDate, String companyName, Pageable pageable) {

        if (!this.securityService.isAdmin()) { // Filtered by company for common users

            Company company = this.securityService.getMe().getCompany();
            return (fromDate != null && toDate != null)
                    ? this.investmentRepository.findAllByDateBetweenAndCompanyName(
                    fromDate, toDate, company.getName(), pageable)
                    : this.investmentRepository.findAllByCompany(company, pageable);

        } else { // Admin privileges

            // Period and company as filter
            if (fromDate != null && toDate != null && companyName != null)
                return this.investmentRepository.findAllByDateBetweenAndCompanyName(
                        fromDate, toDate, companyName, pageable);

            // Using only company as filter
            if ((fromDate == null || toDate == null) && companyName != null)
                return this.investmentRepository.findAllByCompanyName(companyName, pageable);

            // Filter only between a specific period
            if (fromDate != null && toDate != null)
                return this.investmentRepository.findAllByDateBetween(fromDate, toDate, pageable);

            return this.investmentRepository.findAll(pageable);
        }
    }



}
