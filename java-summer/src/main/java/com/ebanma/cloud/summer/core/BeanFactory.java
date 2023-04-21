package com.ebanma.cloud.summer.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 于秦涛
 * @version $ Id: BeanFactory, v 0.1 2023/04/03 10:56 98077 Exp $
 */
public class BeanFactory {

    // 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
    // 任务二：对外提供获取实例对象的接口

    /**
     * 存储对象
     */
    private static Map<String, Object> map = new HashMap<>();

    /**
     * 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
     */
    static {
        // 加载xml文件
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element element = beanList.get(i);
                // 处理每个bean元素，获取id和class属性
                String id = element.attributeValue("id");
                String clazz = element.attributeValue("class");
                // 实例化对象，存起来
                Class<?> aClass = Class.forName(clazz);
                Object instance = aClass.newInstance();

                map.put(id, instance);
            }

            // 解析依赖注入
            List<Element> propertyList = rootElement.selectNodes("//property");
            for (int i = 0; i < propertyList.size(); i++) {
                Element element = propertyList.get(i);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");

                // 找到当前需要被注入依赖的bean
                Element parent = element.getParent();

                // 从容器中获取父元素的实例
                String parentId = parent.attributeValue("id");
                Object parentObject = map.get(parentId);

                // 使用反射进行依赖注入
                Method[] methods = parentObject.getClass().getMethods();
                for (int j = 0; j < methods.length; j++) {
                    Method method = methods[j];
                    if (method.getName().equalsIgnoreCase("set" + name)) {
                        // 匹配上了，说明该方法是 setAccountDao()
                        method.invoke(parentObject, map.get(ref));
                    }
                }
                map.put(parentId, parentObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 任务二：对外提供获取实例对象的接口
     *
     * @param id id
     * @return {@link Object}
     */
    public static Object getBean(String id) {
        return map.get(id);
    }

}

