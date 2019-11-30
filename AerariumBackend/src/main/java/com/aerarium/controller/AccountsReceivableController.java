package com.aerarium.controller;

import com.aerarium.model.AccountsReceivable;
import com.aerarium.projection.AccountsReceivableProjection;
import com.aerarium.service.AccountsReceivableService;
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
public class AccountsReceivableController {

    private final AccountsReceivableService accountsReceivableService;

    private final ProjectionFactory projectionFactory;

    private final PagedResourcesAssembler<AccountsReceivableProjection> pagedResourcesAssembler;

    @Autowired
    public AccountsReceivableController(AccountsReceivableService accountsReceivableService,
                                        ProjectionFactory projectionFactory,
                                        PagedResourcesAssembler pagedResourcesAssembler) {
        this.accountsReceivableService = accountsReceivableService;
        this.projectionFactory = projectionFactory;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(path = "/accountsReceivables", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<AccountsReceivableProjection>>> get(
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "fromDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
            @RequestParam(value = "toDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
            Pageable pageable) {
        Page<AccountsReceivable> accountsReceivables = this.accountsReceivableService.get(fromDate, toDate, companyName, pageable);
        Page<AccountsReceivableProjection> projected = accountsReceivables.map(accountsReceivable ->
                projectionFactory.createProjection(AccountsReceivableProjection.class, accountsReceivable));
        PagedResources<Resource<AccountsReceivableProjection>> resources = this.pagedResourcesAssembler.toResource(projected);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

}
