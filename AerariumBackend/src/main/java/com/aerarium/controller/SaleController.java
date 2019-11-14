package com.aerarium.controller;

import com.aerarium.model.Sale;
import com.aerarium.projection.SaleProjection;
import com.aerarium.service.SaleService;
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
public class SaleController {

    private final SaleService saleService;

    private final ProjectionFactory projectionFactory;

    private final PagedResourcesAssembler<SaleProjection> pagedResourcesAssembler;

    @Autowired
    public SaleController(SaleService saleService,
                             ProjectionFactory projectionFactory,
                             PagedResourcesAssembler pagedResourcesAssembler) {
        this.saleService = saleService;
        this.projectionFactory = projectionFactory;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(path = "/sales", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<SaleProjection>>> get(
            @RequestParam(value = "companyName", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") String companyName,
            @RequestParam(value = "fromDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
            @RequestParam(value = "toDate", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate,
            Pageable pageable) {
        Page<Sale> sales = this.saleService.get(fromDate, toDate, companyName, pageable);
        Page<SaleProjection> projected = sales.map(sale ->
                projectionFactory.createProjection(SaleProjection.class, sale));
        PagedResources<Resource<SaleProjection>> resources = this.pagedResourcesAssembler.toResource(projected);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

}
