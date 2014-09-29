package com.quantasnet.qlan.config;

import javax.sql.DataSource;

import org.hibernate.dialect.HSQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

@Configuration
@Profile("filedb")
public class HSQLDBConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        final ComboPooledDataSource jpaDataSource = new ComboPooledDataSource();
        jpaDataSource.setDriverClass(org.hsqldb.jdbc.JDBCDriver.class.getCanonicalName());
        jpaDataSource.setJdbcUrl("jdbc:hsqldb:file:qlandb");
        jpaDataSource.setUser("sa");
        jpaDataSource.setAcquireIncrement(5);
        jpaDataSource.setIdleConnectionTestPeriod(60);
        jpaDataSource.setMaxPoolSize(50);
        jpaDataSource.setMinPoolSize(10);
        jpaDataSource.setMaxStatements(50);
        return jpaDataSource;
    }

    @Bean
    public Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.put("javax.persistence.sharedCache.mode", "ENABLE_SELECTIVE");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", HSQLDialect.class.getCanonicalName());
        return properties;
    }
	
}
