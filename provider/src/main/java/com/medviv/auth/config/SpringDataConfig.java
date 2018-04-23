package com.medviv.auth.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration for Spring Data/JPA using annotations. This assumes the
 * datasource is configured as a jndi resource as in tomcat's server.xml with
 * name {@code jndiDBresource}
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.medviv.auth.repository")
public class SpringDataConfig {
    @Autowired
    private Environment env;

    @Bean
    EntityManagerFactory entityManagerFactory() throws NamingException,
    PropertyVetoException {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setJpaProperties(hibernateProperties());
        factory.setPackagesToScan("com.medviv.auth.repository.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));

        DatabasePopulatorUtils.execute(dbPopulator(), ds);

        return ds;
    }

    private DatabasePopulator dbPopulator() {
        final ResourceDatabasePopulator dp = new ResourceDatabasePopulator();
        dp.addScript(new ClassPathResource("META-INF/data/drop.sql"));
        dp.addScript(new ClassPathResource("META-INF/data/schema.sql"));
        dp.addScript(new ClassPathResource("META-INF/data/test-data.sql"));

        return dp;
    }

    @Bean
    Properties hibernateProperties() {
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return props;
    }

    @Bean
    PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    /**
     * @return persistence exception translator
     */
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
