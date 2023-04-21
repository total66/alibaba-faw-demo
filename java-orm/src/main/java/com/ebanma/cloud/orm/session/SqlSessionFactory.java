package com.ebanma.cloud.orm.session;

/**
 * @author 于秦涛
 * @version $ Id: SqlSessionFactory, v 0.1 2023/03/09 10:36 98077 Exp $
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
