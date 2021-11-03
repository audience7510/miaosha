package com.github.order.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.github.order.pojo.vo.SendMessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MQReceiver
 * @Author audience
 * @Date 2021/11/3
 * @Version 1.0
 * @Description
 */
@Component
@Slf4j
public class MQReceiver {

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receiveMiaoshaMessage(String message) {
        log.info("MQ接收下单消息："+message);
        SendMessageVO sendMessageVO = JSON.parseObject(message, SendMessageVO.class);
        Long goodsId = sendMessageVO.getGoodsId();
        //判断数据库库存是否小于0
        //根据用户id和商品id判断用户是否秒杀过了
        //商品减库存，新增秒杀订单
    }
}
