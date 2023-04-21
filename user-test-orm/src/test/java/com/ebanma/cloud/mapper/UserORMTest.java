package com.ebanma.cloud.mapper;

import com.ebanma.cloud.domain.entity.UserDO;
import com.ebanma.cloud.orm.session.SqlSession;
import com.ebanma.cloud.orm.session.SqlSessionFactory;
import com.ebanma.cloud.orm.session.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: UserORMTest, v 0.1 2023/03/09 15:04 98077 Exp $
 */
public class UserORMTest {

    @Test
    public void selectOne() throws Exception {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build("sqlMapConfig.xml");
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setUsername("username1");

        UserDO one = sqlSession.selectOne("user.selectOne", userDO);
        System.out.println(one);

    }

    @Test
    public void selectList() throws Exception {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build("sqlMapConfig.xml");
        SqlSession sqlSession=sqlSessionFactory.openSession();

        List<UserDO> one = sqlSession.selectList("user.selectList");
        System.out.println(one);
    }

}

