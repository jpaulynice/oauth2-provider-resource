package com.zikko.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zikko.auth.model.Account;

@Service
public class AuditorServiceImpl implements AuditorAware<String> {
    private static final Logger log = LoggerFactory.getLogger(AuditorServiceImpl.class);

    @Override
    public String getCurrentAuditor() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();

        String currentUser = "anonymous";
        if ((authentication != null) && (authentication.getPrincipal() instanceof Account)) {
            final Account userDetails = (Account) authentication.getPrincipal();
            currentUser = userDetails.getUsername();
        }

        log.debug("user from context={}", currentUser);

        return currentUser;
    }
}