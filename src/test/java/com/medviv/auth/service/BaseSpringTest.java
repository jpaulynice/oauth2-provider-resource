package com.medviv.auth.service;

import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.medviv.auth.config.SpringTestConfig;

/**
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Transactional
@ContextConfiguration(classes = SpringTestConfig.class)
@TestExecutionListeners(listeners = { ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, WithSecurityContextTestExecutionListener.class })
public class BaseSpringTest extends AbstractTestNGSpringContextTests {
    // nothing to see here
}