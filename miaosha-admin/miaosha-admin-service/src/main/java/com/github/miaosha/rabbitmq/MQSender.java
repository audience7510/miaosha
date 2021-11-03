package com.github.miaosha.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.github.miaosha.pojo.po.MiaoshaMessage;
import com.github.miaosha.pojo.vo.SendMessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName MQSender
 * @Author audience
 * @Date 2021/11/2
 * @Version 1.0
 * @Description
 */
@Component
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMiaoshaMessage(SendMessageVO message) {
        String jsonString = JSON.toJSONString(message);
        log.info("MQ发送下单消息："+jsonString);
        rabbitTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE,jsonString);
    }
}
