package com.aerarium.controller;

import com.aerarium.model.AccountsPayable;
import com.aerarium.projection.AccountsPayableProjection;
import com.aerarium.service.AccountsPayableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@BasePathAwareController
public class AccountsPayableController {

    private final AccountsPayableService accountsPayableService;

    private final ProjectionFactory projectionFactory;

    private final PagedResourcesAssembler<AccountsPayableProjection> pagedResourcesAssembler;

    @Autowired
    public AccountsPayableController(AccountsPayableService accountsPayableService,
                                     ProjectionFactory projectionFactory,
                                     PagedResourcesAssembler pagedResourcesAssembler) {
        this.accountsPayableService = accountsPayableService;
        this.projectionFactory = projectionFactory;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(path = "/accountsPayables", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<AccountsPayableProjection>>> get(
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "fromDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
            @RequestParam(value = "toDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
            Pageable pageable) {
        Page<AccountsPayable> accountsPayables = this.accountsPayableService
                .get(fromDate, toDate, companyName, pageable);
        Page<AccountsPayableProjection> projected = accountsPayables.map(accountsPayable ->
                projectionFactory.createProjection(AccountsPayableProjection.class, accountsPayable));
        PagedResources<Resource<AccountsPayableProjection>> resources = this.pagedResourcesAssembler
                .toResource(projected);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

}
