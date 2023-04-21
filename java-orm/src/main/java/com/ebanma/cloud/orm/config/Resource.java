package com.ebanma.cloud.orm.config;

import java.io.InputStream;

/**
 * @author 于秦涛
 * @version $ Id: Resource, v 0.1 2023/03/09 10:48 98077 Exp $
 */
public class Resource {

    /**
     * 根据文件路径转换成字节流
     *
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path){
        //通过类加载器、相对路径获取到文件
        return XmlConfigurationBuilder.class.getClassLoader().getResourceAsStream(path);
    }

}

