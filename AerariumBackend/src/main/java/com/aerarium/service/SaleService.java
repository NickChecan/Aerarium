package com.aerarium.service;

import com.aerarium.model.Company;
import com.aerarium.model.Sale;
import com.aerarium.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    private final SecurityService securityService;

    @Autowired
    public SaleService(SaleRepository saleRepository, SecurityService securityService) {
        this.saleRepository = saleRepository;
        this.securityService = securityService;
    }

    public Page<Sale> get(Date fromDate, Date toDate, String companyName, Pageable pageable) {

        if (!this.securityService.isAdmin()) { // Filtered by company for common users

            Company company = this.securityService.getMe().getCompany();
            return (fromDate != null && toDate != null)
                    ? this.saleRepository.findAllByDateBetweenAndCompanyName(
                            fromDate, toDate, company.getName(), pageable)
                    : this.saleRepository.findAllByCompany(company, pageable);

        } else { // Admin privileges

            // Period and company as filter
            if (fromDate != null && toDate != null && companyName != null)
                return this.saleRepository.findAllByDateBetweenAndCompanyName(
                        fromDate, toDate, companyName, pageable);

            // Using only company as filter
            if ((fromDate == null || toDate == null) && companyName != null)
                return this.saleRepository.findAllByCompanyName(companyName, pageable);

            // Filter only between a specific period
            if (fromDate != null && toDate != null)
                return this.saleRepository.findAllByDateBetween(fromDate, toDate, pageable);

            return this.saleRepository.findAll(pageable);
        }
    }

}
