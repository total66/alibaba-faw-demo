package com.ebanma.cloud.orm.session;

import com.ebanma.cloud.orm.model.Configuration;

/**
 * @author 于秦涛
 * @version $ Id: DefaultSqlSessionFactory, v 0.1 2023/03/09 15:14 98077 Exp $
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration=configuration;
    }
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}

