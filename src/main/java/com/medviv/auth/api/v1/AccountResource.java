package com.medviv.auth.api.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medviv.auth.service.AccountService;
import com.medviv.model.Account;

@Component
@Path("accounts")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class AccountResource {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    private final AccountService service;

    @Autowired
    public AccountResource(final AccountService service) {
        this.service = service;
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response register(final Account account) {
        log.debug("Register account {}", account);

        final Account created = service.createAccount(account);

        return Response.ok(created).build();
    }
}