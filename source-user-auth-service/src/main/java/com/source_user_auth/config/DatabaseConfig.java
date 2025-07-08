//package com.source_user_auth.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
//
//    // PostgreSQL
//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource")
//    public DataSourceProperties postgresDatasourceProperties() { return new DataSourceProperties(); }
//
//    @Bean(name = "dataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.hiraki")
//    public DataSource dataSource() { return postgresDatasourceProperties().initializeDataSourceBuilder().build(); }
//
//    @Bean(name = "jdbcDataSource")
//    @Primary
//    public JdbcTemplate jdbcDataSource(@Qualifier(value = "dataSource") DataSource jdbcDataSource) {
//        return new JdbcTemplate(jdbcDataSource);
//    }
//
//    @Bean(name = "jdbcPermissionDatabase")
//    public JdbcTemplate jdbcPermissionDatabase() { return new JdbcTemplate(new HikariDataSource()); }
//}
