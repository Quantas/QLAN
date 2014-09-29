package com.quantasnet.qlan.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by andrewlandsverk on 9/28/14.
 */
@Configuration
@Profile("!filedb")
public class MySQLConfig {

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

    @Bean
    public Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.put("javax.persistence.sharedCache.mode", "ENABLE_SELECTIVE");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", MySQLDialect.class.getCanonicalName());
        return properties;
    }
}
