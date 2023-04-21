package com.ebanma.cloud.mapper.dao;

import com.ebanma.cloud.domain.entity.UserDO;

import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: UserDao, v 0.1 2023/03/10 14:12 98077 Exp $
 */
public interface UserDao {

    List<UserDO> findAll() throws Exception;

    UserDO findByCondition(UserDO userDO) throws Exception;

}

