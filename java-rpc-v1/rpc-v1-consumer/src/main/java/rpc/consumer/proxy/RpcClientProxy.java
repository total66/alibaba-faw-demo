package rpc.consumer.proxy;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.rpc.api.rpc.common.RpcRequest;
import com.ebanma.cloud.rpc.api.rpc.common.RpcResponse;
import rpc.consumer.client.RpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author 于秦涛
 * @version $ Id: RpcClientProxy, v 0.1 2023/05/12 21:15 98077 Exp $
 * 客户端代理类-创建代理对象
 * 1.封装request请求对象
 * 2.创建RpcClient对象
 * 3.发送消息
 * 4.返回结果
 */

public class RpcClientProxy {

    public static Object createProxy(Class serviceClass) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{serviceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //1.封装request请求对象
                RpcRequest rpcRequest = new RpcRequest();
                rpcRequest.setRequestId(UUID.randomUUID().toString());
                rpcRequest.setClassName(method.getDeclaringClass().getName());
                rpcRequest.setMethodName(method.getName());
                rpcRequest.setParameterTypes(method.getParameterTypes());
                rpcRequest.setParameters(args);
                //2.创建RpcClient对象
                RpcClient rpcClient = new RpcClient("127.0.0.1", 8899);
                try {
                    //3.发送消息
                    Object responseMsg = rpcClient.send(JSON.toJSONString(rpcRequest));
                    RpcResponse rpcResponse = JSON.parseObject(responseMsg.toString(), RpcResponse.class);

                    //4.返回结果
                    Object result = rpcResponse.getReturnValue();
                    return JSON.parseObject(result.toString(), method.getReturnType());
                } catch (Exception e) {
                    throw e;
                } finally {
                    rpcClient.close();
                }
            }
        });
    }

}

