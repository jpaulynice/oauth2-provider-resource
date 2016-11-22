package com.medviv.auth.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.testng.annotations.Test;

public class MockAuditorServiceTest extends BaseSpringTest {

    @Autowired
    private AuditorAware<String> auditService;

    @Test
    public void testNotNull() {
        assertNotNull(auditService);
    }

    @Test
    public void testGetCurrentAuditorAnom() {
        assertEquals(auditService.getCurrentAuditor(), "anonymous");
    }
}