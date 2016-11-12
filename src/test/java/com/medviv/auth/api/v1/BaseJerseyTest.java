package com.medviv.auth.api.v1;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.medviv.auth.api.Application;
import com.medviv.auth.config.SpringTestConfig;

/**
 * Base test class for all jersey unit tests
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public abstract class BaseJerseyTest extends JerseyTest {
    /*
     * (non-Javadoc)
     *
     * @see org.glassfish.jersey.test.JerseyTest#setUp()
     */
    @Override
    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.glassfish.jersey.test.JerseyTest#tearDown()
     */
    @Override
    @AfterClass
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.glassfish.jersey.test.JerseyTest#configure()
     */
    @Override
    protected Application configure() {
        forceSet(TestProperties.CONTAINER_PORT, "0");
        final Application app = new Application();
        app.property("contextConfig", new AnnotationConfigApplicationContext(SpringTestConfig.class));
        return app;
    }
}