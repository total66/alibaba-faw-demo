package com.ebanma.cloud.orm.session;

import com.ebanma.cloud.orm.model.Configuration;
import com.ebanma.cloud.orm.model.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: Executor, v 0.1 2023/03/09 15:19 98077 Exp $
 */
public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    int delete(Configuration configuration, MappedStatement mappedStatement, Object... params);


    //int delete(Configuration configuration, MappedStatement mappedStatement, Object[] params);
}
