package com.ebanma.cloud.orm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ebanma.cloud.orm.model.Configuration;
import com.ebanma.cloud.orm.model.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author 于秦涛
 * @version $ Id: XmlConfigurationBuilder, v 0.1 2023/03/09 10:44 98077 Exp $
 */
public class XmlConfigurationBuilder {

    private static Configuration configuration = new Configuration();

    public static Configuration build(InputStream inputStream) throws DocumentException {
        //使用dom4j对sqlMapConfig.xml配置文件进行解析
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> propertyList = rootElement.selectNodes("//property");

        //todo :properties 工具类
        Properties properties = new Properties();
        propertyList.forEach((Element element) -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        });

        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getProperty("driverClass"));
        druidDataSource.setUrl(properties.getProperty("jdbcUrl"));
        druidDataSource.setUsername(properties.getProperty("username"));
        druidDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(druidDataSource);

        List<Element> mapperList=rootElement.selectNodes("//mapper");
        mapperList.forEach((Element mapper)->{
            String resource = mapper.attributeValue("resource");
            InputStream resourceAsStream=Resource.getResourceAsStream(resource);

            try {
                Document mapperDocument = new SAXReader().read(resourceAsStream);
                //<mapper namespace="user">
                Element rootElement1=mapperDocument.getRootElement();
                String namespace = rootElement1.attributeValue("namespace");

                List<Element> selectList=rootElement1.selectNodes("//select");
                selectList.forEach((Element select)->{
                    String id = select.attributeValue("id");
                    String resultType= select.attributeValue("resultType");
                    String parameterType=select.attributeValue("parameterType");
                    String sqlText=select.getTextTrim();

                    MappedStatement mappedStatement=new MappedStatement();
                    mappedStatement.setId(id);
                    mappedStatement.setParamterType(parameterType);
                    mappedStatement.setResultType(resultType);
                    mappedStatement.setSql(sqlText);

                    String key = namespace+"."+id;

                    configuration.getMappedStatementMap().put(key,mappedStatement);
                });
            } catch (DocumentException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        });

        return configuration;
    }

}

