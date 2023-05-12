package rpc.consumer;

import com.ebanma.cloud.rpc.api.UserApi;
import com.ebanma.cloud.rpc.api.rpc.dto.UserInfoDTO;
import rpc.consumer.proxy.RpcClientProxy;

/**
 * 测试类
 */
public class ClientBootStrap {
    public static void main(String[] args) {
        UserApi userService = (UserApi) RpcClientProxy.createProxy(UserApi.class);
        UserInfoDTO user = userService.getById(1);
        System.out.println(user);
    }
}