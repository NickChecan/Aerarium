package com.aerarium.controller;

import com.aerarium.model.Expense;
import com.aerarium.projection.ExpenseProjection;
import com.aerarium.service.ExpenseService;
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
public class ExpenseController {

    private final ExpenseService expenseService;

    private final ProjectionFactory projectionFactory;

    private final PagedResourcesAssembler<ExpenseProjection> pagedResourcesAssembler;

    @Autowired
    public ExpenseController(ExpenseService expenseService,
                             ProjectionFactory projectionFactory,
                             PagedResourcesAssembler pagedResourcesAssembler) {
        this.expenseService = expenseService;
        this.projectionFactory = projectionFactory;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(path = "/expenses", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<ExpenseProjection>>> get(
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "fromDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
            @RequestParam(value = "toDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
            Pageable pageable) {
        Page<Expense> expenses = this.expenseService.get(fromDate, toDate, companyName, pageable);
        Page<ExpenseProjection> projected = expenses.map(expense ->
                projectionFactory.createProjection(ExpenseProjection.class, expense));
        PagedResources<Resource<ExpenseProjection>> resources = this.pagedResourcesAssembler.toResource(projected);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

}
