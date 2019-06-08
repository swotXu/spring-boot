package com.xuh.meituan.mqtools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TicketReceive {
    private Logger logger = LoggerFactory.getLogger(TicketReceive.class);

    @RabbitListener(queues = "ticket.queue.response")
    public void processc(String msg){
        // 抢票结果
        logger.info(Thread.currentThread().getName()+"从ticket.queue.response队列中收到消息，抢票结果为：" + msg);
    }
}
