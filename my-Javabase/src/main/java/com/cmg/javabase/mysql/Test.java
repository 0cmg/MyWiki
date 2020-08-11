package com.cmg.javabase.mysql;

import com.cmg.javabase.mysql.entity.User;
import com.cmg.javabase.mysql.service.JdbcTemplateConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Name MyWiki com.cmg.javabase.mysql
 * @Author cmg
 * @date 2020/8/11 21:39
 * @Description TODO
 **/
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JdbcTemplateConfiguration.class);
        JdbcTemplate jdbcTemplate = annotationConfigApplicationContext.getBean(JdbcTemplate.class);
        List<User> list = jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSex(rs.getString("sex"));
                user.setAddr(rs.getString("addr"));

                return user;
            }
        });
        System.out.println(list);
    }
}
