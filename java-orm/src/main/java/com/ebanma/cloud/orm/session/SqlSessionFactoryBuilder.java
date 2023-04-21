package com.ebanma.cloud.orm.session;

import com.ebanma.cloud.orm.config.Resource;
import com.ebanma.cloud.orm.config.XmlConfigurationBuilder;
import com.ebanma.cloud.orm.model.Configuration;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author 于秦涛
 * @version $ Id: SqlSessionFactoryBuild, v 0.1 2023/03/09 10:38 98077 Exp $
 */
public class SqlSessionFactoryBuilder {

    public static SqlSessionFactory build(InputStream inputStream) throws DocumentException {

        // 1.使用dom4j解析配置文件，将解析出来的内容封装到Configuration
        Configuration configuration = XmlConfigurationBuilder.build(inputStream);

        // 2.创建sqlSessionFactory对象：工厂类，生产sqlSession

        return new DefaultSqlSessionFactory(configuration);

    }

    public static SqlSessionFactory build(String path) throws DocumentException {
        return build(Resource.getResourceAsStream(path));
    }



}

