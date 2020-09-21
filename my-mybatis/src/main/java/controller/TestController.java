package controller;

import DAO.SingleTransMapper;
import DTO.SingletransDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestController {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        //加载资源
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            //获取单笔映射对象
            SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
            //根据流水号查询
            SingletransDTO blog = mapper.selectSingle("642EHDCS899XKF8P");

            if(blog != null) {
                System.out.println(blog);
            }else{
                System.out.println("not found");
            }

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
