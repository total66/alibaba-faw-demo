package com.ebanma.cloud.orm.session;

import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: SqlSession, v 0.1 2023/03/09 10:32 98077 Exp $
 */
public interface SqlSession {

    <E> List<E> selectList(String statementId,Object... params) throws Exception;
    // todo: 工厂模式
    <T> T selectOne(String statementId,Object... params) throws Exception;

    <T> int delete(String statementId, Object... params) throws Exception;

    <T> T getMapper(Class<?> mapperClass) throws Exception;

}
