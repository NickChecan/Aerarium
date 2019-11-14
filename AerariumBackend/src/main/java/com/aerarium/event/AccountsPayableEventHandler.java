package com.aerarium.event;

import com.aerarium.model.AccountsPayable;
import com.aerarium.model.User;
import com.aerarium.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class AccountsPayableEventHandler {

    private final SecurityService securityService;

    @Autowired
    public AccountsPayableEventHandler(SecurityService securityService) {
        this.securityService = securityService;
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handleAccountsPayableCompany(AccountsPayable accountsPayable) {
        // Set company for non administration users
        if (!securityService.isAdmin()) {
            User user = securityService.getMe();
            accountsPayable.setCompany(user.getCompany());
        }
    }

}
