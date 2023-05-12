package com.ebanma.cloud.rpc.api.rpc.common;

import lombok.Data;

/**
 * 封装的响应对象
 */
@Data
public class RpcResponse {

    /**
     * 响应ID
     */
    private String requestId;

    /**
     * 返回的结果
     */
    private Object returnValue;

}