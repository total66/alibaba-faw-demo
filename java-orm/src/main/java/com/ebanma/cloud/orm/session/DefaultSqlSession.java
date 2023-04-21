package com.ebanma.cloud.orm.session;

import com.ebanma.cloud.orm.model.Configuration;
import com.ebanma.cloud.orm.model.MappedStatement;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 于秦涛
 * @version $ Id: DefaultSqlSession, v 0.1 2023/03/09 15:17 98077 Exp $
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration=configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId,Object... params) throws Exception{
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        Executor executor = new DefaultExecutor();
        List<Object> list = executor. query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception{
        List<Object> list = selectList(statementId,params);
        if(list.size()==1){
            return (T) list.get(0);
        }

        return (T) new SQLException("没有或者太多");
    }

    @Override
    public int delete(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        Executor executor = new DefaultExecutor();
        int i = executor.delete(configuration, mappedStatement, params);
        return i;
    }

    //通过动态代理来实现接口声明的方法。
    @Override
    public <T> T getMapper(Class<?> mapperClass) throws Exception {

        //动态代理来处理
        // ClassLoader loader, 用哪个类加载器去加载代理对象
        // Class<?>[] interfaces, 动态代理类需要实现的接口
        // InvocationHandler h, 动态代理类需要实现的业务逻辑，会调用h里面的invoke方法去执行
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            // Object proxy, 当前代理对象的引用
            // Method method, 当前被调用方法的引用
            // Object[] args, 当前被调用方法的参数
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // String statement , Object... params
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." +methodName;

                //ParameterizedType就是参数化类型的意思
                //声明类型中带有<>的都是参数化类型，这里我们特指selectList的返回值 List<T>
                Type returnType = method.getGenericReturnType();
                if(returnType instanceof ParameterizedType){
                    return selectList(statementId,args);
                }
                return selectOne(statementId,args);
            }
        });

        return (T) proxyInstance;
    }

}

