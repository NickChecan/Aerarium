package com.aerarium.controller;

import com.aerarium.model.Investment;
import com.aerarium.projection.InvestmentProjection;
import com.aerarium.service.InvestmentService;
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
public class InvestmentController {

    private final InvestmentService investmentService;

    private final ProjectionFactory projectionFactory;

    private final PagedResourcesAssembler<InvestmentProjection> pagedResourcesAssembler;

    @Autowired
    public InvestmentController(InvestmentService investmentService,
                             ProjectionFactory projectionFactory,
                             PagedResourcesAssembler pagedResourcesAssembler) {
        this.investmentService = investmentService;
        this.projectionFactory = projectionFactory;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(path = "/investments", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<InvestmentProjection>>> get(
            @RequestParam(value = "companyName", required = false) String companyName,
            @RequestParam(value = "fromDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
            @RequestParam(value = "toDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
            Pageable pageable) {
        Page<Investment> investments = this.investmentService.get(fromDate, toDate, companyName, pageable);
        Page<InvestmentProjection> projected = investments.map(investment ->
                projectionFactory.createProjection(InvestmentProjection.class, investment));
        PagedResources<Resource<InvestmentProjection>> resources = this.pagedResourcesAssembler.toResource(projected);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

}
