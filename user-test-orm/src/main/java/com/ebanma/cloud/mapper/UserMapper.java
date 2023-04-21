package com.ebanma.cloud.mapper;

import com.ebanma.cloud.domain.entity.UserDO;

import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: UserMapper, v 0.1 2023/03/10 14:19 98077 Exp $
 */
public interface UserMapper {

    List<UserDO> findAll() throws Exception;

    UserDO findByCondition(UserDO userDO) throws Exception;

    int deleteByUserName(UserDO userDO);
}
