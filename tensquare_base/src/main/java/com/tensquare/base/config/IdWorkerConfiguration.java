package com.tensquare.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.IdWorker;

@Configuration
@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerConfiguration {

    @Autowired
    private IdWorkerProperties prop;


    @Bean
    public IdWorker getIdWorker() {
        return new IdWorker(prop.getWorkerId(), prop.getDatacenterId());
    }

}
