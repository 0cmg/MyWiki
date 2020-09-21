package controller;

import DAO.SingleTransMapper;
import DTO.SingleCondition;
import DTO.SingletransDTO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class TestController {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        //加载资源
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建session
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {

//            SingletransDTO blog = selectSingle(session);
//            SingletransDTO blog = selectSingleByClass(session);
//            SingletransDTO blog = selectSingleByMultCondition(session);
            SingletransDTO blog = selectSingleByParam(session);
            if(blog != null) {
                System.out.println(blog);
            }else{
                System.out.println("not found");
            }

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static SingletransDTO selectSingle(SqlSession session){
        //获取单笔映射对象
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        //根据流水号查询
        SingletransDTO blog = mapper.selectSingle("642EHDCS899XKF8P");
        return blog;
    }

    public static SingletransDTO selectSingleByClass(SqlSession session){
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        SingletransDTO blog = mapper.selectSingleByClass(new SingleCondition( "642EHDCS899XKF8P","123"));
        return blog;
    }
    public static SingletransDTO selectSingleByMultCondition(SqlSession session){
        //获取单笔映射对象
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        //根据流水号查询
        HashMap<String,Object> param = new HashMap<String,Object>();
        param.put("transNo","642EHDCS899XKF8P");
        param.put("commandCode","123");
        SingletransDTO blog = mapper.selectSingleByMultCondition(param);
        return blog;
    }

    public static SingletransDTO selectSingleByParam(SqlSession session){
        //获取单笔映射对象
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        SingletransDTO blog = mapper.selectSingleByParam("642EHDCS899XKF8P","123");
        return blog;
    }
}
