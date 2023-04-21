package com.ebanma.cloud.mapper;

import com.ebanma.cloud.domain.entity.UserDO;
import com.ebanma.cloud.orm.session.SqlSession;
import com.ebanma.cloud.orm.session.SqlSessionFactory;
import com.ebanma.cloud.orm.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: UserMapperTest, v 0.1 2023/03/10 14:39 98077 Exp $
 */
public class UserMapperTest {

    @Test
    public void selectOne() throws Exception{
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build("sqlMapConfig.xml");
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setUsername("username1");

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserDO one = mapper.findByCondition(userDO);
        System.out.println(one);

    }

    @Test
    public void selectList() throws Exception{
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build("sqlMapConfig.xml");
        SqlSession sqlSession=sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<UserDO> one = mapper.findAll();
        System.out.println(one);
    }

    @Test
    public void deleteByUserName() throws Exception {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build("sqlMapConfig.xml");
        SqlSession sqlSession=sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setUsername("username1");

        int i = mapper.deleteByUserName(userDO);
        System.out.println(i);;
    }

}

