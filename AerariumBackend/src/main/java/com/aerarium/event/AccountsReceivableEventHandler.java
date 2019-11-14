package com.aerarium.event;

import com.aerarium.model.AccountsReceivable;
import com.aerarium.model.User;
import com.aerarium.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class AccountsReceivableEventHandler {

    private final SecurityService securityService;

    @Autowired
    public AccountsReceivableEventHandler(SecurityService securityService) {
        this.securityService = securityService;
    }

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handleAccountsReceivableCompany(AccountsReceivable accountsReceivable) {
        // Set company for non administration users
        if (!securityService.isAdmin()) {
            User user = securityService.getMe();
            accountsReceivable.setCompany(user.getCompany());
        }
    }

}
