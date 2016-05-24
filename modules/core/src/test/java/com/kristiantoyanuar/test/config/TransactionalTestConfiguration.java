package com.kristiantoyanuar.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by Kristianto Yanuar on 5/21/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.kristiantoyanuar.repo")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TransactionalTestConfiguration extends AbstractTestConfiguration {
    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .build();
        return db;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.kristiantoyanuar.model");
        factory.setDataSource(dataSource());
        factory.getJpaPropertyMap().put("hibernate.dialect", environment.getProperty("spring.jpa.database-platform"));
        factory.getJpaPropertyMap().put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        factory.getJpaPropertyMap().put("hibernate.hbm2ddl.import_files",
                environment.getProperty("spring.jpa.hibernate.hbm2ddl.import_files"));
        factory.getJpaPropertyMap().put("hibernate.hbm2dll.auto",
                environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        factory.afterPropertiesSet();

        return factory.getObject();
    }


    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

}
