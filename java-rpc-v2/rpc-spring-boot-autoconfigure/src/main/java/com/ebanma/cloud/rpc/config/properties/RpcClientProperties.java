package com.ebanma.cloud.rpc.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rpc.client")
public class RpcClientProperties {
    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    /**
     * 服务提供者名称
     */
    private String consumerName = "";

}