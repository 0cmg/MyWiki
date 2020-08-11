package com.cmg.springboot;

import com.cmg.springboot.entity.User;
import com.cmg.springboot.service.JDBCTempleteConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {
    @Test
    public void test(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(JDBCTempleteConfig.class);
        JdbcTemplate jdbcTemplate = annotationConfigApplicationContext.getBean(JdbcTemplate.class);
        List<User> list = jdbcTemplate.query("select * from user", new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));;
                user.setName(rs.getString("name"));
                user.setSex(rs.getString("sex"));
                user.setAddr(rs.getString("addr"));
                return user;
            }
        });
        System.out.println(list);
    }

    @Test
    public void test2() throws Exception {
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc.mysql://localhost:3306/sprintboot?characterEncoding=UTF-8&useUnicode=true&useSSL=true&serverTimezone=UTC",
                "root","123456");

        Statement stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM user";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            // 通过字段检索
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            String addr = rs.getString("addr");

            // 输出数据
            System.out.print("ID: " + id);
            System.out.print(", 名字: " + name);
            System.out.print(", 性别: " + sex);
            System.out.print(", 地址: " + addr);
            System.out.print("\n");
        }
        // 完成后关闭
        rs.close();
        stmt.close();
        conn.close();
    }

}
