package com.xuh.ticket12306.mqtools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketSender {
    private Logger logger = LoggerFactory.getLogger(TicketSender.class);
    @Autowired
    private AmqpTemplate template;

    public void send(String exchange,String roukeKey,String context){
        try {
            template.convertAndSend(exchange, roukeKey, context);
        }catch (Exception e){
            logger.info("发送消息：'" + context + "'到该交换机上：'" + exchange + "'出现异常失败！");
        }
    }
}
