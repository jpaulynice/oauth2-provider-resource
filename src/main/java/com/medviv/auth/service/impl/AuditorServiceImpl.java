package com.medviv.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.medviv.auth.model.UserAccount;

@Service
public class AuditorServiceImpl implements AuditorAware<String> {
    private static final Logger log = LoggerFactory.getLogger(AuditorServiceImpl.class);

    @Override
    public String getCurrentAuditor() {
        String currentUser = "anonymous";

        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            final Authentication authentication = context.getAuthentication();

            if ((authentication != null) && (authentication.getPrincipal() instanceof UserAccount)) {
                final UserAccount userDetails = (UserAccount) authentication.getPrincipal();
                currentUser = userDetails.getUsername();
            }

            log.debug("user from context={}", currentUser);
        }

        return currentUser;
    }
}
