package com.example.demo;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.servlet.Filter;
import javax.sql.DataSource;

@Configuration
public class AppConfig extends WebMvcAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix="my.datasource")
    public DataSource myDataSource() {
        return DataSourceBuilder.create().build();
    }

}
