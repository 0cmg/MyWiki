package com.cmg.springboot.service;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Name MyWiki com.cmg.springboot.service
 * @Author cmg
 * @date 2020/8/9 16:20
 * @Description TODO
 **/
@Configuration
@PropertySource(value = "classpath:db.properties")
public class JDBCTempleteConfig {
    @Value("${jdbcDriverClassName}")
    String jdbcDriverClassName;

    @Value("${jdbcUrl}")
    String jdbcUrl;

    @Value("${jdbcUserName}")
    String jdbcUserName;

    @Value("${jdbcPassWord}")
    String jdbcPassWord;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUserName);
        dataSource.setPassword(jdbcPassWord);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
