package com.ebanma.cloud.mapper;

import com.ebanma.cloud.domain.entity.UserDO;
import com.ebanma.cloud.mapper.dao.UserDao;
import com.ebanma.cloud.mapper.dao.UserDaoImpl;
import org.junit.Test;

import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: UserDaoTest, v 0.1 2023/03/10 14:15 98077 Exp $
 */
public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void selectOne() throws Exception {

        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setUsername("username1");

        UserDO one = userDao.findByCondition(userDO);
        System.out.println(one);
    }

    @Test
    public void selectList() throws Exception {

        List<UserDO> users = userDao.findAll();
        users.forEach(user -> System.out.println(user));
    }

}

