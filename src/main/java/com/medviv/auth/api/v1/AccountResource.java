package com.medviv.auth.api.v1;

import static com.medviv.auth.config.Paths.ACCOUNTS_ID;
import static com.medviv.auth.config.Paths.ACCOUNTS_ID_PARAM;
import static com.medviv.auth.config.Paths.ACCOUNTS_URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.medviv.auth.service.AccountService;
import com.medviv.model.Account;

@Component
@Path(ACCOUNTS_URL)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class AccountResource {
    private static final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final AccountService service;

    @Autowired
    public AccountResource(final AccountService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response createAccount(final Account account) {
        log.debug("Create account {}", account);

        final Account created = service.createAccount(account);

        return Response.ok(created).build();
    }

    @PUT
    @Path(ACCOUNTS_ID_PARAM)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateAccount(@PathParam(ACCOUNTS_ID) final Long accountId, final Account account) {
        log.debug("Update account {}", account);

        final Account updated = service.updateAccount(account);

        return Response.ok(updated).build();
    }
}