package com.quantasnet.qlan.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfig {

	@Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        final ComboPooledDataSource jpaDataSource = new ComboPooledDataSource();
        jpaDataSource.setDriverClass(com.mysql.jdbc.Driver.class.getCanonicalName());
        jpaDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/qlan?user=qlan&password=qlan");
        jpaDataSource.setAcquireIncrement(5);
        jpaDataSource.setIdleConnectionTestPeriod(60);
        jpaDataSource.setMaxPoolSize(50);
        jpaDataSource.setMinPoolSize(10);
        jpaDataSource.setMaxStatements(50);
        return jpaDataSource;
    }
	
}
