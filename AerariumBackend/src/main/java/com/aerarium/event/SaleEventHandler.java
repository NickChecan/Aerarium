package com.aerarium.event;

import com.aerarium.model.Sale;
import com.aerarium.model.User;
import com.aerarium.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class SaleEventHandler {

    private final SecurityService securityService;

    @Autowired
    public SaleEventHandler(SecurityService securityService) {
        this.securityService = securityService;
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handleSaleCompany(Sale sale) {
        // Set company for non administration users
        if (!securityService.isAdmin()) {
            User user = securityService.getMe();
            sale.setCompany(user.getCompany());
        }
    }

}
