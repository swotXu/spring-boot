package com.xuh.ticket12306.mqtools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceive {
    private Logger logger =  LoggerFactory.getLogger(FanoutReceive.class);

    @RabbitListener(queues = "queue.a")
    public void queueA(String str){
        logger.info("Aqueue 接收消息："+ str);
    }
    @RabbitListener(queues = "queue.b")
    public void queueB(String str){
        logger.info("Bqueue 接收消息："+ str);
    }
    @RabbitListener(queues = "queue.c")
    public void queueC(String str){
        logger.info("Cqueue 接收消息："+ str);
    }
}
