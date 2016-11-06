package com.ohmyapp.boot;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.bind.PropertySourcesPropertyValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Emerald on 11/5/2016.
 * test config
 */
@ComponentScan
@Configuration
public class TestConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test");
        ds.setUsername("test");
        ds.setPassword("test");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        ds.setConnectionProperties(properties);
        return ds;
    }
}
