package com.tensquare.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tensquare.sms")
public class SmsProperties {

    private String accessKeyID;

    private String accessKeySecret;

    private String signName;

    private String verifyCodeTemplate;
}
