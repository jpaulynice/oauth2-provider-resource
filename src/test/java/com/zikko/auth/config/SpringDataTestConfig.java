package com.zikko.auth.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.zikko.auth.repository")
@EnableTransactionManagement
public class SpringDataTestConfig {
    @Bean
    EntityManagerFactory entityManagerFactory() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(false);

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("com.zikko.auth.repository.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("classpath:sql/drop.sql")
                .addScript("classpath:sql/schema.sql").addScript("classpath:sql/test-data.sql").build();
    }

    @Bean
    JndiObjectFactoryBean jndiDataSource() throws IllegalStateException, NamingException {
        final String jndiName = "java:comp/env/foo";
        final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind(jndiName, dataSource());
        builder.activate();

        final JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(jndiName);
        bean.setResourceRef(true);

        return bean;
    }

    @Bean
    PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}