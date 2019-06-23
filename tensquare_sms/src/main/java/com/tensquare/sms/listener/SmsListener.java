package com.tensquare.sms.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tensquare.sms.config.SmsProperties;
import com.tensquare.sms.util.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private SmsProperties smsProperties;


    /**
     * 短信验证码
     *
     * @param msg 数据
     */
    @RabbitListener(queues = "sms")
    public void verifyCode(Map<String, String> msg) throws JsonProcessingException {
        if (CollectionUtils.isEmpty(msg)) {
            return;
        }
        String phoneNumber = msg.remove("phoneNumber");
        if (StringUtils.isEmpty(phoneNumber)) {
            return;
        }
        // 发送短信
        smsUtil.sendSms(phoneNumber, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate(), new ObjectMapper().writeValueAsString(msg));
    }

}
