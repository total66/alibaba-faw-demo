package com.ebanma.cloud.orm.session;

import com.ebanma.cloud.orm.model.BoundSql;
import com.ebanma.cloud.orm.model.Configuration;
import com.ebanma.cloud.orm.model.MappedStatement;
import com.ebanma.cloud.orm.util.GenericTokenParser;
import com.ebanma.cloud.orm.util.ParameterMapping;
import com.ebanma.cloud.orm.util.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: DefaultExecutor, v 0.1 2023/03/09 15:21 98077 Exp $
 */
public class DefaultExecutor implements Executor {

    /**
     * 1.加载数据库驱动
     * 2.通过驱动管理类获取数据库链接
     * 3.定义sql语句?表示占位符
     * 4.获取预处理statement
     * 5.设置参数，第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
     * 6.向数据库发出sql执行查询，查询出结果集
     * 7.遍历查询结果集
     * 8.并封装返回对象
     */

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception{

        //2.通过驱动管理类获取数据库链接
        Connection connection = configuration.getDataSource().getConnection();

        //3.定义sql语句?表示占位符
        //select * from tb_user where id = #{id} and username = #{username}
        //select * from tb_user where id = ? and username = ?

        String sql = mappedStatement.getSql();

        BoundSql boundSql = getBoundSql(sql);

        // 4.获取预处理statement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 5.设置参数，第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
        String parameterType = mappedStatement.getParamterType();
        Class<?> parameterTypeClass = parameterType!= null?Class.forName(parameterType) : null;

        List<ParameterMapping> parameterMappingList=boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();

            // java.lang.Class类的getDeclaredField()方法用于返回一个Field对象，该对象指示该类的给定声明字段。
            Field declaredField = parameterTypeClass.getDeclaredField(content);
            // 暴力访问
            declaredField.setAccessible(true);
            // java.lang.reflect.Field的get()方法用于获取字段对象的值
            Object object = declaredField.get(params[0]);

            preparedStatement.setObject(i+1,object);
        }

        // 6.向数据库发出sql执行查询，查询出结果集

        ResultSet resultSet = preparedStatement.executeQuery();

        // 7.遍历结果集

        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = resultType != null?Class.forName(resultType):null;

        List<Object> resultList = new ArrayList<>();
        while (resultSet.next()){
            ResultSetMetaData metaData = resultSet.getMetaData();
            Object instance = resultTypeClass.newInstance();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //获取字段名
                String columnName = metaData.getColumnName(i);
                //获取字段值
                Object columnValue = resultSet.getObject(columnName);

                // todo:内省,给对象的属性赋值。
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(instance,columnValue);
            }

            resultList.add(instance);

        }

        return (List<E>) resultList;


    }

    @Override
    public int delete(Configuration configuration, MappedStatement mappedStatement, Object... params) {
        return 0;
    }

    private BoundSql getBoundSql(String sql) {

        // 1.将#{}使用？代替
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parse = genericTokenParser.parse(sql);

        // 2.解析出#{}里面的值
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parse, parameterMappings);

    }
}

