package com.tensquare.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("tensquare.worker")
public class IdWorkerProperties {

    /**
     * 节点ID
     */
    private Long workerId;

    /**
     * 机器ID
     */
    private Long datacenterId;

}
