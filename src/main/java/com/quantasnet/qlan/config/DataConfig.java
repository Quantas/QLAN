package com.quantasnet.qlan.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.quantasnet.qlan.repo")
@EnableTransactionManagement
public class DataConfig {
	
	@Autowired
	private DataSource dataSource;

    @Autowired
    private Properties jpaProperties;

	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactory() throws Exception {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPackagesToScan("com.quantasnet.qlan.domain");
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateAdapter());
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws Exception {
		return localContainerEntityManagerFactory().getObject();
	}

	@Bean
	public JpaVendorAdapter hibernateAdapter() {
		final HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws Exception {
		return new JpaTransactionManager(entityManagerFactory());
	}
}