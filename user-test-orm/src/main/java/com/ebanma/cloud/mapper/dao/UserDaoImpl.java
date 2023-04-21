package com.ebanma.cloud.mapper.dao;

import com.ebanma.cloud.domain.entity.UserDO;
import com.ebanma.cloud.orm.session.SqlSession;
import com.ebanma.cloud.orm.session.SqlSessionFactory;
import com.ebanma.cloud.orm.session.SqlSessionFactoryBuilder;

import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: UserDaoImpl, v 0.1 2023/03/10 14:13 98077 Exp $
 */
public class UserDaoImpl implements UserDao{
    @Override
    public List<UserDO> findAll() throws Exception {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build("sqlMapConfig.xml");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession.selectList("user.selectList");
    }

    @Override
    public UserDO findByCondition(UserDO userDO) throws Exception {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build("sqlMapConfig.xml");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        return sqlSession.selectOne("user.selectOne", userDO);
    }
}

