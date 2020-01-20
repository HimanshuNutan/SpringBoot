package com.airlinesmanagement.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@PropertySource("classpath:application.properties")
@Configuration
public class DBConfiguration {

    private static final Logger LOGGER =
            LogManager.getLogger(DBConfiguration.class.getName());
    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean("jdbcTemplate")
     public JdbcTemplate getDataSource(){
         JdbcTemplate jdbcTemplate = null;
        System.out.println(driverClassName+"  "+url+"  "+username+"  "+password);
         try {
             DriverManagerDataSource dataSource = new DriverManagerDataSource();
             dataSource.setDriverClassName(driverClassName);
             dataSource.setUrl(url);
             dataSource.setUsername(username);
             dataSource.setPassword(password);
             jdbcTemplate = new JdbcTemplate(dataSource);

         }catch (Exception e){
LOGGER.error("Error getting JDBCTemplate",e);
         }
         return jdbcTemplate;}
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}






