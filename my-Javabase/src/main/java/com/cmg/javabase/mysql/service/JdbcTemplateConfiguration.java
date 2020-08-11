package com.cmg.javabase.mysql.service;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Name MyWiki com.cmg.javabase.mysql.service
 * @Author cmg
 * @date 2020/8/11 21:27
 * @Description TODO
 **/
@Configuration
@PropertySource(value = ("classpath:db.properties"))
public class JdbcTemplateConfiguration {
    @Value("${jdbcDriverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbcUrl}")
    private String jdbcUrl;

    @Value("${jdbcUserName}")
    private String jdbcUserName;

    @Value("${jdbcPassWord}")
    private String jdbcPassWord;

    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(jdbcDriverClassName);
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(jdbcUserName);
        druidDataSource.setPassword(jdbcPassWord);
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
