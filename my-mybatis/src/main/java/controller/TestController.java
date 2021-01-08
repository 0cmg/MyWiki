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

            selectSingle(session);
            selectSingleByClass(session);
            selectSingleByMultCondition(session);
            selectSingleByParam(session);
            insert(session);
            update(session);
            delete(session);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void selectSingle(SqlSession session) {
        //获取单笔映射对象
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        //根据流水号查询
        SingletransDTO blog = mapper.selectSingle("MYBATIS.INSERT");
        if (blog != null) {
            System.out.println(blog);
        } else {
            System.out.println("not found");
        }
    }

    public static void selectSingleByClass(SqlSession session) {
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        SingletransDTO blog = mapper.selectSingleByClass(new SingleCondition("642EHDCS899XKF8P", "123"));
        if (blog != null) {
            System.out.println(blog);
        } else {
            System.out.println("not found");
        }
    }

    public static void selectSingleByMultCondition(SqlSession session) {
        //获取单笔映射对象
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        //根据流水号查询
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("transNo", "642EHDCS899XKF8P");
        param.put("commandCode", "123");
        SingletransDTO blog = mapper.selectSingleByMultCondition(param);
        if (blog != null) {
            System.out.println(blog);
        } else {
            System.out.println("not found");
        }
    }

    public static void selectSingleByParam(SqlSession session) {
        //获取单笔映射对象
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        SingletransDTO blog = mapper.selectSingleByParam("642EHDCS899XKF8P", "123");
        if (blog != null) {
            System.out.println(blog);
        } else {
            System.out.println("not found");
        }
    }

    public static void insert(SqlSession session) {
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        SingletransDTO singletransDTO = new SingletransDTO();
        singletransDTO.EnterpriseNum = "QT330001";
        singletransDTO.TransNo = "MYBATIS.INSERT";
        singletransDTO.CommandCode = "10009";
        int count = mapper.insert(singletransDTO);
        session.commit();
        System.out.println("insert result:" + count);
    }

    public static void update(SqlSession session) {
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        SingletransDTO singletransDTO = new SingletransDTO();
        singletransDTO.EnterpriseNum = "QT330001";
        singletransDTO.TransNo = "MYBATIS.INSERT";
        singletransDTO.CommandCode = "10009";
        singletransDTO.State = 2;
        int count = mapper.update(singletransDTO);
        session.commit();
        System.out.println("update result:" + count);
    }

    public static void delete(SqlSession session) {
        SingleTransMapper mapper = session.getMapper(SingleTransMapper.class);
        int count = mapper.delete("MYBATIS.INSERT", "10009");
        session.commit();
        System.out.println("delete result:" + count);
    }
}
